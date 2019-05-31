package com.ayokunlepaul.payee

import androidx.lifecycle.ViewModel
import com.ayokunlepaul.payee.core.repository.daos.BankDAO
import com.ayokunlepaul.payee.core.repository.daos.TransactionDAO
import com.ayokunlepaul.payee.core.repository.entities.ATMCard
import com.ayokunlepaul.payee.core.repository.entities.Bank
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by ayokunlepaul on 2019-05-27.
 */
class MainActivityViewModel @Inject constructor(
    private val bankDAO: BankDAO,
    private val transactionDAO: TransactionDAO
): ViewModel() {

    init {
        bankDAO.getBanksCount().flatMapCompletable { if (it == 0) bankDAO.saveBank(BankUtils.getBanks()) else Completable.complete() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun isValidPin(pinEntered: String, atmCardNumber: Long): Boolean {
        return getAccountWithATM(atmCardNumber).account.card.cardPin.toString() == pinEntered
    }

    fun getAllCards() = bankDAO.getAllBanks()

    fun getAccountWithATM(atmCardNumber: Long): Bank {
        return bankDAO.getAllBanks().blockingGet().find {
            it.account.card.cardNumber == atmCardNumber
        }!!
    }
}