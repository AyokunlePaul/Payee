package com.ayokunlepaul.payee.core.di.modules.activities

import com.ayokunlepaul.payee.fragments.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by ayokunlepaul on 2019-05-27.
 */
@Module
abstract class MainActivityFragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindStartFragment(): StartTransactionFragment

    @ContributesAndroidInjector
    abstract fun bindBankCardsFragment(): BankCardsFragment

    @ContributesAndroidInjector
    abstract fun bindCardInformationFragment(): CardInformationFragment

    @ContributesAndroidInjector
    abstract fun bindWithdrawFragment(): WithdrawFragment

    @ContributesAndroidInjector
    abstract fun bindMainFragment(): MainFragment
}