package com.ayokunlepaul.payee

import androidx.fragment.app.FragmentManager
import com.ayokunlepaul.payee.fragments.BankCardsFragment
import com.ayokunlepaul.payee.fragments.StartTransactionFragment
import com.ayokunlepaul.payee.fragments.WithdrawFragment

/**
 * Created by ayokunlepaul on 2019-05-26.
 */
class Payee {

    private lateinit var fragmentManager: FragmentManager

    fun start() {
        val cardsFragment = BankCardsFragment()
        cardsFragment.show(fragmentManager, BankCardsFragment.TAG)
    }

    fun newTransaction(atmCardNumber: Long, pinEntered: String) {
        val startFragment = StartTransactionFragment.getInstance(atmCardNumber, pinEntered)
        startFragment.show(fragmentManager, StartTransactionFragment.TAG)
    }

    fun withdraw(atmCardNumber: Long) {
        val withdrawFragment = WithdrawFragment.getInstance(atmCardNumber)
        withdrawFragment.show(fragmentManager, WithdrawFragment.TAG)
    }

    class Builder (private val manager: FragmentManager) {

        fun build(): Payee {
            return Payee().apply {
                fragmentManager = manager
            }
        }
    }
}