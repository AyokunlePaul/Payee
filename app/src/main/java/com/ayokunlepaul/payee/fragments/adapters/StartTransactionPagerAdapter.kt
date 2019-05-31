package com.ayokunlepaul.payee.fragments.adapters

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ayokunlepaul.payee.AutoUpdateRecyclerView
import com.ayokunlepaul.payee.core.base.BaseViewHolder
import com.ayokunlepaul.payee.databinding.LayoutTransactionsBinding
import com.ayokunlepaul.payee.databinding.LayoutWithdrawBinding
import kotlin.properties.Delegates

/**
 * Created by ayokunlepaul on 2019-05-29.
 */
class StartTransactionPagerAdapter : RecyclerView.Adapter<BaseViewHolder<Any>>(), AutoUpdateRecyclerView {
    private var transactionLayout: List<ViewDataBinding> by Delegates.observable(emptyList()) { _, oldBindings, newBindings ->
        autoNotify(oldBindings, newBindings) { oldBinding, newBinding ->
            oldBinding.toString() == newBinding.toString()
        }
    }

    private var isWithdraw = false
    private lateinit var transactionClickListener: TransactionsClickListener
    private lateinit var withdrawClickListener: WithdrawClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> = when (viewType) {
        TRANSACTIONS -> StartTransactionViewHolder(transactionLayout[0] as LayoutTransactionsBinding)
        else -> WithdrawViewHolder(transactionLayout[1] as LayoutWithdrawBinding)
    }

    override fun getItemCount(): Int = transactionLayout.size

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> TRANSACTIONS
        else -> WITHDRAW
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {
        holder.bindItem(null)
    }

    fun addNewLayoutBinding(binding: ViewDataBinding) {
        transactionLayout = transactionLayout.toMutableList().apply {
            add(binding)
        }.toList()
    }

    fun removeWithdrawBinding() {
        transactionLayout = transactionLayout.toMutableList().apply {
            removeAt(1)
        }.toList()
    }

    fun setClickListeners(transactionListener: TransactionsClickListener, withdrawClickListener: WithdrawClickListener) {
        this.transactionClickListener = transactionListener
        this.withdrawClickListener = withdrawClickListener
    }

    inner class StartTransactionViewHolder (private val binding: LayoutTransactionsBinding): BaseViewHolder<Any>(binding) {
        override fun bindItem(value: Any?) {
            binding.withdraw.setOnClickListener {
                isWithdraw = true
                transactionClickListener.onWithdrawClicked()
            }
            binding.checkBalance.setOnClickListener {
                isWithdraw = false
                transactionClickListener.onCheckBalanceClicked()
            }
        }
    }

    inner class WithdrawViewHolder(private val binding: LayoutWithdrawBinding): BaseViewHolder<Any>(binding) {
        override fun bindItem(value: Any?) {
            binding.withdraw500.setOnClickListener {
                withdrawClickListener.onClicked(500.00)
            }
            binding.withdraw1000.setOnClickListener {
                withdrawClickListener.onClicked(1000.00)
            }
            binding.withdraw2000.setOnClickListener {
                withdrawClickListener.onClicked(2000.00)
            }
            binding.withdraw5000.setOnClickListener {
                withdrawClickListener.onClicked(5000.00)
            }
            binding.withdraw10000.setOnClickListener {
                withdrawClickListener.onClicked(10000.00)
            }
            binding.withdraw20000.setOnClickListener {
                withdrawClickListener.onClicked(20000.00)
            }
        }
    }

    interface TransactionsClickListener {
        fun onWithdrawClicked()
        fun onCheckBalanceClicked()
    }

    interface WithdrawClickListener {
        fun onClicked(amount: Double)
    }

    companion object {
        const val TRANSACTIONS = 0
        const val WITHDRAW = 1
        const val CHECK_BALANCE = 2
    }
}