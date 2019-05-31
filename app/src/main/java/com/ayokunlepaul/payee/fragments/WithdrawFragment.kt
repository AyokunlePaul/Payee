package com.ayokunlepaul.payee.fragments

import android.os.Bundle
import com.ayokunlepaul.payee.PayeeActivityCallback
import com.ayokunlepaul.payee.PayeeConstants
import com.ayokunlepaul.payee.R
import com.ayokunlepaul.payee.core.base.BaseBottomFragment
import com.ayokunlepaul.payee.databinding.LayoutWithdrawBinding

/**
 * Created by ayokunlepaul on 2019-05-31.
 */
class WithdrawFragment: BaseBottomFragment<LayoutWithdrawBinding>() {

    private lateinit var activityCallback: PayeeActivityCallback

    override fun getLayoutResource(): Int = R.layout.layout_withdraw

    override fun setup(binding: LayoutWithdrawBinding) {
        binding.withdraw500.setOnClickListener {
            activityCallback.showSnackbar(binding.root, "Withdraw ${500} functionality coming soon...")
            dismiss()
        }
        binding.withdraw1000.setOnClickListener {
            activityCallback.showSnackbar(binding.root, "Withdraw ${1000} functionality coming soon...")
            dismiss()
        }
        binding.withdraw2000.setOnClickListener {
            activityCallback.showSnackbar(binding.root, "Withdraw ${2000} functionality coming soon...")
            dismiss()
        }
        binding.withdraw5000.setOnClickListener {
            activityCallback.showSnackbar(binding.root, "Withdraw ${5000} functionality coming soon...")
            dismiss()
        }
        binding.withdraw10000.setOnClickListener {
            activityCallback.showSnackbar(binding.root, "Withdraw ${10000} functionality coming soon...")
            dismiss()
        }
        binding.withdraw20000.setOnClickListener {
            activityCallback.showSnackbar(binding.root, "Withdraw ${20000} functionality coming soon...")
            dismiss()
        }
    }

    companion object {
        const val TAG = "Withdraw Fragment"

        fun getInstance(atmCardNumber: Long): WithdrawFragment {
            return WithdrawFragment().apply {
                arguments = Bundle().apply {
                    putLong(PayeeConstants.PAYEE_ATM_CARD_NUMBER, atmCardNumber)
                }
            }
        }
    }
}