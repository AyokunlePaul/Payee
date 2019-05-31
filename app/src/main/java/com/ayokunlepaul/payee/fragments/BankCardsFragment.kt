package com.ayokunlepaul.payee.fragments

import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayokunlepaul.payee.*
import com.ayokunlepaul.payee.core.base.BaseBottomFragment
import com.ayokunlepaul.payee.core.repository.entities.ATMCard
import com.ayokunlepaul.payee.databinding.FragmentBankCardsBinding
import com.ayokunlepaul.payee.databinding.LayoutDialogTitleBinding
import com.ayokunlepaul.payee.databinding.LayoutPinEntryBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by ayokunlepaul on 2019-05-28.
 */
class BankCardsFragment: BaseBottomFragment<FragmentBankCardsBinding>(), ATMsAdapter.OnCardClickListener {

    @Inject lateinit var viewModel: MainActivityViewModel

    private lateinit var binding: FragmentBankCardsBinding
    private lateinit var contentBinding: LayoutPinEntryBinding

    private val atmAdapter by lazy { ATMsAdapter(context!!).apply {
        setCardClickListener { this@BankCardsFragment }
    } }
    private lateinit var payeeNavigation: PayeeNavigation
    private lateinit var activityCallback: PayeeActivityCallback

    override fun getLayoutResource(): Int = R.layout.fragment_bank_cards

    override fun setup(binding: FragmentBankCardsBinding) {
        this.binding = binding
        binding.bankCards.apply {
            layoutManager = LinearLayoutManager(context!!, RecyclerView.HORIZONTAL, false)
            adapter = atmAdapter
        }
        viewModel.getAllCards().doOnSuccess { banks ->
            atmAdapter.cards = banks.map { it.account.card }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    override fun onCardClicked(card: ATMCard) {
        dismiss()
        val dialog = MaterialAlertDialogBuilder(context).apply {
            val titleBinding = LayoutDialogTitleBinding.inflate(layoutInflater, binding.root as ViewGroup, false).apply {
                title = "Enter Your Pin"
            }
            contentBinding = LayoutPinEntryBinding.inflate(layoutInflater, binding.root as ViewGroup, false)
            setView(contentBinding.root)
            setCustomTitle(titleBinding.root)
            setCancelable(false)
            setPositiveButton(R.string.confirm_pin, null)
            setNeutralButton(R.string.back) { dialog, _ ->
                dialog.dismiss()
            }
        }.create()
        dialog.setOnShowListener { dialogInterface ->
            val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {
                val pinEntered = contentBinding.atmPin.text.toString()
                if (TextUtils.isEmpty(pinEntered)) {
                    activityCallback.showSnackbar(binding.root, "No pin entered", true)
                    return@setOnClickListener
                }
                dialogInterface.dismiss()
                payeeNavigation.gotoCardDetailsFragment(card, pinEntered)
            }
        }
        dialog.show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        payeeNavigation = (context as MainActivity)
        activityCallback = context
    }

    companion object {
        const val TAG = "Bank Cards Fragment"
    }
}