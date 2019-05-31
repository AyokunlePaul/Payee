package com.ayokunlepaul.payee

import com.ayokunlepaul.payee.core.repository.entities.ATMCard
import com.ayokunlepaul.payee.core.repository.entities.Bank
import com.ayokunlepaul.payee.core.repository.entities.BankAccount

/**
 * Created by ayokunlepaul on 2019-05-28.
 */
object BankUtils {
    @JvmStatic
    fun getBanks(): List<Bank> {
        val firstBankAccount = BankAccount("Babatope Oni",
            1122334455,
            9_000_000.00,
            ATMCard(
                1234_5678_9012_3456,
                "Babatope Oni",
                122,
                2929,
                "First Bank",
                1122334455
            )
        )
        val fcmbAccount = BankAccount(
            "Tolulope Adediran",
            9988776655,
            10_000_000.00,
            ATMCard(
                2345_6789_0123_4567,
                "Tolulope Adediran",
                233,
                3030,
                "First City Monumental Bank",
                9988776655
            )
        )
        val gtBankAccount = BankAccount(
            "Busola Ekundayo",
            3456567878,
            6_000_000.00,
            ATMCard(
                3456_7890_1234_5678,
                "Busola Ekundayo",
                344,
                4141,
                "Guarantee Trust Bank",
                3456567878
            )
        )
        val sterlingBankAccount = BankAccount(
            "Fahim Saleh",
            7362536273,
            100_000_000.00,
            ATMCard(
                4567_8901_2345_6789,
                "Fahim Saleh",
                455,
                5252,
                "Sterling Bank",
                7362536273
            )
        )
        val firstBank = Bank(
            bankName = "First Bank",
            account = firstBankAccount
        )
        val fcmb = Bank(
            bankName = "First City Monumental Bank",
            account = fcmbAccount
        )
        val gtBank = Bank(
            bankName = "Guarantee Trust Bank",
            account = gtBankAccount
        )
        val sterlingBank = Bank(
            bankName = "Sterling Bank",
            account = sterlingBankAccount
        )

        return listOf(firstBank, fcmb, gtBank, sterlingBank)
    }

    @JvmStatic
    fun formatCardNumber(cardNumber: Long): String {
        var newAccount = ""
        for (value in 1..cardNumber.toString().length) {
            newAccount += cardNumber.toString()[value - 1]
            if (value % 4 == 0) newAccount += " "
        }
        return newAccount
    }
}

