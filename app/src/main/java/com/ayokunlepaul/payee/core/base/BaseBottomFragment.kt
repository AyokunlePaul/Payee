package com.ayokunlepaul.payee.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by ayokunlepaul on 2019-05-26.
 */
abstract class BaseBottomFragment<D: ViewDataBinding>: BottomSheetDialogFragment(), HasSupportFragmentInjector {

    @Inject lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    @LayoutRes abstract fun getLayoutResource(): Int

    abstract fun setup(binding: D)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: D = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false)
        setup(binding)

        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = childFragmentInjector
}