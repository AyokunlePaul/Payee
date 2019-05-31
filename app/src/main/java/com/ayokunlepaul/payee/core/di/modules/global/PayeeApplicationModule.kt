package com.ayokunlepaul.payee.core.di.modules.global

import android.content.Context
import com.ayokunlepaul.payee.PayeeApplication
import com.ayokunlepaul.payee.core.di.modules.global.repository.PayeeRoomModule
import com.ayokunlepaul.payee.core.di.scopes.PayeeAppScope
import dagger.Binds
import dagger.Module

/**
 * Created by ayokunlepaul on 2019-04-26.
 */
@Module(includes = [PayeeViewModelModule::class, PayeeRoomModule::class])
abstract class PayeeApplicationModule {

    @Binds
    @PayeeAppScope
    internal abstract fun bindKaamruApplicationContext(application: PayeeApplication): Context
}