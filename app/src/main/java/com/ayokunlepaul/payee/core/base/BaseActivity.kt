package com.ayokunlepaul.payee.core.base

import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.ayokunlepaul.payee.R
import com.ayokunlepaul.payee.databinding.LayoutProgressDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by ayokunlepaul on 2019-05-27.
 */
abstract class BaseActivity<in D: ViewDataBinding, out V: ViewModel>: DaggerAppCompatActivity() {

    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        initializeDataBinding()
        createDialog()
    }

    private fun initializeDataBinding(){
        val binding: D = DataBindingUtil.setContentView(this, getLayoutResourceId())
        binding.setVariable(getBindingVariable(), getViewModel())
        binding.executePendingBindings()
        getBinding(binding)
    }

    private fun createDialog() {
        val builder = MaterialAlertDialogBuilder(this)
        builder.setView(LayoutProgressDialogBinding.inflate(layoutInflater).root)
        builder.setCancelable(false)

        dialog = builder.create()
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun hideKeyboard(activity: Activity) {
        val imm = getSystemService<InputMethodManager>()
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm!!.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showLoadingDialog() {
        dialog.show()
    }

    fun dismissLoadingDialog() {
        if (dialog.isShowing) dialog.dismiss()
    }

    fun showSnackBar(rootView: View, text: String, isError: Boolean = false, duration: Int = Snackbar.LENGTH_SHORT){
        val snackBar = Snackbar.make(rootView, text, duration)
        val param = snackBar.view.layoutParams as CoordinatorLayout.LayoutParams
        val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
        if (isError) snackBarLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.red)) else snackBarLayout.setBackgroundColor(
            ContextCompat.getColor(this, R.color.colorPrimary))
        snackBarLayout.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).setTextColor(resources.getColor(android.R.color.white))
        param.gravity = Gravity.TOP
        snackBar.view.layoutParams = param

        snackBar.show()
    }

    abstract fun getViewModel(): V

    @LayoutRes
    abstract fun getLayoutResourceId(): Int

    abstract fun getBindingVariable(): Int

    abstract fun getBinding(binding: D)
}