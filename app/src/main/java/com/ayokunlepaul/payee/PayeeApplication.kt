package com.ayokunlepaul.payee

import com.ayokunlepaul.payee.core.di.components.DaggerPayeeApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by ayokunlepaul on 2019-05-26.
 */
class PayeeApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerPayeeApplicationComponent.builder().bindPayeeApplicationInstance(this).buildPayeeApplicationComponent()
    }
}