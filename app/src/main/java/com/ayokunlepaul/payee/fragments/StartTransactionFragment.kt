package com.ayokunlepaul.payee.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.ViewGroup
import com.ayokunlepaul.payee.*
import com.ayokunlepaul.payee.core.base.BaseBottomFragment
import com.ayokunlepaul.payee.databinding.FragmentStartBinding
import com.ayokunlepaul.payee.databinding.LayoutDialogTitleBinding
import com.ayokunlepaul.payee.databinding.LayoutPinEntryBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

/**
 * Created by ayokunlepaul on 2019-05-26.
 */
class StartTransactionFragment: BaseBottomFragment<FragmentStartBinding>() {

    @Inject lateinit var mainActivityViewModel: MainActivityViewModel
    private val payee by lazy { (activity as MainActivity).payee }
    private val atmCardNumber by lazy { arguments!!.getLong(PayeeConstants.PAYEE_ATM_CARD_NUMBER) }
    private val pinEntered by lazy { arguments!!.getString(PayeeConstants.PAYEE_PIN_ENTERED) }

    private lateinit var binding: FragmentStartBinding
    private lateinit var activityCallback: PayeeActivityCallback

    override fun getLayoutResource(): Int = R.layout.fragment_start

    override fun setup(binding: FragmentStartBinding) {
        this.binding = binding
        binding.checkBalance.setOnClickListener {
            if (!mainActivityViewModel.isValidPin(pinEntered, atmCardNumber)) {
                activityCallback.showSnackbar(binding.root, "The pin entered is invalid!", true)
                showEnterPinDialog(TYPE_CHECK_BALANCE)
                return@setOnClickListener
            }
            showBalance()
        }
        binding.withdraw.setOnClickListener {
            dismiss()
            if (!mainActivityViewModel.isValidPin(pinEntered, atmCardNumber)) {
                activityCallback.showSnackbar(binding.root, "The pin entered is invalid!", true)
                showEnterPinDialog(TYPE_WITHDRAW)
                return@setOnClickListener
            }
            payee.withdraw(atmCardNumber)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityCallback = context as MainActivity
    }

    private fun showBalance() {
        MaterialAlertDialogBuilder(context).apply {
            val titleBinding = LayoutDialogTitleBinding.inflate(layoutInflater, binding.root as ViewGroup, false).apply {
                title = "Account Balance"
            }
            setCustomTitle(titleBinding.root)
            val bank = mainActivityViewModel.getAccountWithATM(atmCardNumber)
            val message = "Dear ${bank.account.accountName}\nYour account balance is ${bank.account.currentBalance}"
            setMessage(message)
            setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
        }.create().show()
    }

    private fun showEnterPinDialog(type: Int) {
        MaterialAlertDialogBuilder(context).apply {
            val titleBinding = LayoutDialogTitleBinding.inflate(layoutInflater, binding.root as ViewGroup, false).apply {
                title = "Enter Your Pin"
            }
            val contentBinding = LayoutPinEntryBinding.inflate(layoutInflater, binding.root as ViewGroup, false)
            setView(contentBinding.root)
            setCustomTitle(titleBinding.root)
            setCancelable(false)
            setPositiveButton(R.string.confirm_pin) { dialog, _ ->
                val pinEntered = contentBinding.atmPin.text.toString()
                if (TextUtils.isEmpty(pinEntered)) {
                    activityCallback.showSnackbar(binding.root, "No pin entered", true)
                    return@setPositiveButton
                }
                when (type) {
                    TYPE_CHECK_BALANCE -> showBalance()
                    TYPE_WITHDRAW -> payee.withdraw(atmCardNumber)
                }
                dialog.dismiss()
            }
            setNeutralButton(R.string.back) { dialog, _ ->
                dialog.dismiss()
            }
        }.create().show()
    }

    companion object {
        const val TAG = "Start Transaction Fragment"

        const val TYPE_WITHDRAW = 0
        const val TYPE_CHECK_BALANCE = 1

        fun getInstance(atmCardNumber: Long, pinEntered: String): StartTransactionFragment {
            return StartTransactionFragment().apply {
                arguments = Bundle().apply {
                    putLong(PayeeConstants.PAYEE_ATM_CARD_NUMBER, atmCardNumber)
                    putString(PayeeConstants.PAYEE_PIN_ENTERED, pinEntered)
                }
            }
        }
    }
}