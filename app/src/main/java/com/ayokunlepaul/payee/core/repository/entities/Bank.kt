package com.ayokunlepaul.payee.core.repository.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by ayokunlepaul on 2019-05-27.
 */
@Entity
data class Bank (
    @PrimaryKey(autoGenerate = true) val bankId: Int = 0,
    val bankName: String,
    val account: BankAccount
)