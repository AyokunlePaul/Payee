package com.ayokunlepaul.payee.fragments

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayokunlepaul.payee.MainActivity
import com.ayokunlepaul.payee.R
import com.ayokunlepaul.payee.core.base.BaseFragment
import com.ayokunlepaul.payee.databinding.FragmentCardInformationBinding

/**
 * Created by ayokunlepaul on 2019-05-28.
 */
class CardInformationFragment: BaseFragment<FragmentCardInformationBinding>(), Toolbar.OnMenuItemClickListener {

    private val navigationController by lazy { findNavController() }
    private val cardInformationFragmentArgs by navArgs<CardInformationFragmentArgs>()
    private val card by lazy { cardInformationFragmentArgs.ATMCard }
    private val pinEntered by lazy { cardInformationFragmentArgs.PinEntered }
    private val transactionAdapter by lazy { CardTransactionAdapter(context!!) }
    private val payee by lazy { (activity as MainActivity).payee }

    override fun getLayoutResource(): Int = R.layout.fragment_card_information

    override fun setup(binding: FragmentCardInformationBinding) {
        binding.toolbar3.apply {
            setupWithNavController(navigationController)
            inflateMenu(R.menu.new_menu)
            setOnMenuItemClickListener(this@CardInformationFragment)
        }
        binding.card = card
        binding.transactions.apply{
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        payee.newTransaction(card.cardNumber, pinEntered)
        return true
    }
}