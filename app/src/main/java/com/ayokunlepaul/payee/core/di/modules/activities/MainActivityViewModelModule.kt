package com.ayokunlepaul.payee.core.di.modules.activities

import androidx.lifecycle.ViewModel
import com.ayokunlepaul.payee.MainActivityViewModel
import com.ayokunlepaul.payee.core.di.keys.PayeeViewModelKey
import com.ayokunlepaul.payee.core.di.scopes.PerActivity
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by ayokunlepaul on 2019-05-27.
 */
@Module
abstract class MainActivityViewModelModule {

    @Binds
    @IntoMap
    @PayeeViewModelKey(MainActivityViewModel::class)
    @PerActivity
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel
}