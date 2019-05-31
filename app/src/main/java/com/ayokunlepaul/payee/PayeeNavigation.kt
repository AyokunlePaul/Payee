package com.ayokunlepaul.payee

import com.ayokunlepaul.payee.core.repository.entities.ATMCard

/**
 * Created by ayokunlepaul on 2019-05-29.
 */
interface PayeeNavigation {
    fun gotoCardDetailsFragment(card: ATMCard, pinEntered: String)
}