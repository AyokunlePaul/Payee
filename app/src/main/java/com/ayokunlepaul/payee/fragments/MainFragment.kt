package com.ayokunlepaul.payee.fragments

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ayokunlepaul.payee.MainActivity
import com.ayokunlepaul.payee.MainActivityViewModel
import com.ayokunlepaul.payee.R
import com.ayokunlepaul.payee.core.base.BaseFragment
import com.ayokunlepaul.payee.databinding.FragmentMainBinding
import javax.inject.Inject

/**
 * Created by ayokunlepaul on 2019-05-28.
 */
class MainFragment: BaseFragment<FragmentMainBinding>() {

    private val payee by lazy { (activity as MainActivity).payee }
    private val navigationController by lazy { findNavController() }

    override fun getLayoutResource(): Int = R.layout.fragment_main

    override fun setup(binding: FragmentMainBinding) {
        binding.toolbar.setupWithNavController(navigationController)
        binding.start.setOnClickListener {
            payee.start()
        }
    }
}