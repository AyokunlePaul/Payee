package com.ayokunlepaul.payee.core.repository.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by ayokunlepaul on 2019-05-28.
 */
@Entity
data class TransactionEntity (
    @PrimaryKey val transactionId: String,
    val fromAccount: String,
    val toAccount: String,
    val amountTransferred: String,
    val reason: String
)