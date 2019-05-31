package com.ayokunlepaul.payee

import android.view.View

/**
 * Created by ayokunlepaul on 2019-05-31.
 */
interface PayeeActivityCallback {

    fun showSnackbar(root: View, message: String, isError: Boolean = false)
}