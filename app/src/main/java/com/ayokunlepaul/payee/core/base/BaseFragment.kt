package com.ayokunlepaul.payee.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerFragment

/**
 * Created by ayokunlepaul on 2019-05-28.
 */
abstract class BaseFragment<D: ViewDataBinding>: DaggerFragment() {

    @LayoutRes abstract fun getLayoutResource(): Int

    abstract fun setup(binding: D)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: D = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false)
        setup(binding)

        return binding.root
    }
}