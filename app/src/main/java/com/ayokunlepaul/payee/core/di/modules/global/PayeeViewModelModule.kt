package com.ayokunlepaul.payee.core.di.modules.global

import androidx.lifecycle.ViewModelProvider
import com.ayokunlepaul.payee.PayeeViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by ayokunlepaul on 2019-05-02.
 */
@Module
abstract class PayeeViewModelModule {

    @Binds
    internal abstract fun bindKaamruViewModelFactory(payeeViewModelFactory: PayeeViewModelFactory): ViewModelProvider.Factory
}