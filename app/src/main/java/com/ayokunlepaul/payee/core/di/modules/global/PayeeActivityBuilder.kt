package com.ayokunlepaul.payee.core.di.modules.global

import com.ayokunlepaul.payee.MainActivity
import com.ayokunlepaul.payee.core.di.modules.activities.MainActivityFragmentBuilder
import com.ayokunlepaul.payee.core.di.modules.activities.MainActivityViewModelModule
import com.ayokunlepaul.payee.core.di.scopes.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by ayokunlepaul on 2019-04-26.
 */
@Module
abstract class PayeeActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityViewModelModule::class, MainActivityFragmentBuilder::class])
    abstract fun bindMainActivity(): MainActivity
}