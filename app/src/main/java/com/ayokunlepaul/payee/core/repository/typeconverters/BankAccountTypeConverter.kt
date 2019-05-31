package com.ayokunlepaul.payee.core.repository.typeconverters

import androidx.room.TypeConverter
import com.ayokunlepaul.payee.core.repository.entities.BankAccount
import com.google.gson.Gson

/**
 * Created by ayokunlepaul on 2019-05-28.
 */
class BankAccountTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun convertTo(bankAccount: BankAccount): String = gson.toJson(bankAccount)

    @TypeConverter
    fun convertFrom(bankAccount: String): BankAccount = gson.fromJson(bankAccount, BankAccount::class.java)
}