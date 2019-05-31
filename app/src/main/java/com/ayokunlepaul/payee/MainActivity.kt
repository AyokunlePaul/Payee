package com.ayokunlepaul.payee

import android.view.View
import androidx.navigation.findNavController
import com.ayokunlepaul.payee.core.base.BaseActivity
import com.ayokunlepaul.payee.core.repository.entities.ATMCard
import com.ayokunlepaul.payee.databinding.ActivityMainBinding
import javax.inject.Inject

/**
 * Created by ayokunlepaul on 2019-05-27.
 */
class MainActivity: BaseActivity<ActivityMainBinding, MainActivityViewModel>(), PayeeNavigation, PayeeActivityCallback {

    @Inject lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    val payee = Payee.Builder(supportFragmentManager).build()

    private val navigationController by lazy { findNavController(R.id.navigation_fragment) }

    override fun getViewModel(): MainActivityViewModel = mainActivityViewModel

    override fun getLayoutResourceId(): Int = R.layout.activity_main

    override fun getBindingVariable(): Int = 0

    override fun getBinding(binding: ActivityMainBinding) {
        this.binding = binding
    }

    override fun gotoCardDetailsFragment(card: ATMCard, pinEntered: String) {
        val direction = MainNavigationDirections.gotoCardDetailsFragment(card, pinEntered)
        navigationController.navigate(direction)
    }

        override fun showSnackbar(root: View, message: String, isError: Boolean) = showSnackBar(root, message, isError)
}