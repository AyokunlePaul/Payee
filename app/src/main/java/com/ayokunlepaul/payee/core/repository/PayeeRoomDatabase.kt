package com.ayokunlepaul.payee.core.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ayokunlepaul.payee.core.repository.daos.BankDAO
import com.ayokunlepaul.payee.core.repository.daos.TransactionDAO
import com.ayokunlepaul.payee.core.repository.entities.Bank
import com.ayokunlepaul.payee.core.repository.entities.TransactionEntity
import com.ayokunlepaul.payee.core.repository.typeconverters.BankAccountTypeConverter

/**
 * Created by ayokunlepaul on 2019-05-27.
 */
@Database(entities = [Bank::class, TransactionEntity::class], version = 1)
@TypeConverters(BankAccountTypeConverter::class)
abstract class PayeeRoomDatabase: RoomDatabase() {
    abstract fun getBankDao(): BankDAO
    abstract fun getTransactionDao(): TransactionDAO
}