package com.ayokunlepaul.payee.core.di.components

import com.ayokunlepaul.payee.PayeeApplication
import com.ayokunlepaul.payee.core.di.modules.global.PayeeActivityBuilder
import com.ayokunlepaul.payee.core.di.modules.global.PayeeApplicationModule
import com.ayokunlepaul.payee.core.di.modules.activities.MainActivityFragmentBuilder
import com.ayokunlepaul.payee.core.di.scopes.PayeeAppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

/**
 * Created by ayokunlepaul on 2019-04-26.
 */
@PayeeAppScope
@Component(modules = [AndroidSupportInjectionModule::class, AndroidInjectionModule::class,
    PayeeApplicationModule::class, PayeeActivityBuilder::class])
interface PayeeApplicationComponent: AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance fun bindPayeeApplicationInstance(application: PayeeApplication): Builder
        fun buildPayeeApplicationComponent(): PayeeApplicationComponent
    }
}