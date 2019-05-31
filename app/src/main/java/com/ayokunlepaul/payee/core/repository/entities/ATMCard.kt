package com.ayokunlepaul.payee.core.repository.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by ayokunlepaul on 2019-05-28.
 */
@Parcelize
data class ATMCard (
    val cardNumber: Long,
    val cardHolderName: String,
    val cvv: Int,
    val cardPin: Int,
    val bankName: String,
    val accountNumber: Long
): Parcelable