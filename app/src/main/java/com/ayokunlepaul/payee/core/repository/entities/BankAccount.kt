package com.ayokunlepaul.payee.core.repository.entities

import androidx.room.Entity

/**
 * Created by ayokunlepaul on 2019-05-27.
 */
@Entity
data class BankAccount (
    val accountName: String,
    val accountNumber: Long,
    val currentBalance: Double,
    val card: ATMCard
)