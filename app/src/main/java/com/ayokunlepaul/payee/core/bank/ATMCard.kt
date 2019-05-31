package com.ayokunlepaul.payee.core.bank

/**
 * Created by ayokunlepaul on 2019-05-26.
 */
data class ATMCard (
    val cardPin: Int,
    val cardNumber: Int,
    val cvv: Int
)