package com.ayokunlepaul.payee.core.di.keys

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by ayokunlepaul on 2019-05-02.
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class PayeeViewModelKey(val value: KClass<out ViewModel>)