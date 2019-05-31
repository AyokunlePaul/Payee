package com.ayokunlepaul.payee.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayokunlepaul.payee.AutoUpdateRecyclerView
import com.ayokunlepaul.payee.core.base.BaseViewHolder
import com.ayokunlepaul.payee.core.repository.entities.TransactionEntity as Transaction
import com.ayokunlepaul.payee.databinding.LayoutEmptyStateBinding
import kotlin.properties.Delegates

/**
 * Created by ayokunlepaul on 2019-05-29.
 */
class CardTransactionAdapter(context: Context): RecyclerView.Adapter<BaseViewHolder<Transaction>>(), AutoUpdateRecyclerView {

    private val transactions: List<Transaction> by Delegates.observable(emptyList()) { _, oldTransactions, newTransactions ->
        autoNotify(oldTransactions, newTransactions) { oldTransaction, newTransaction  ->
            oldTransaction.transactionId == newTransaction.transactionId
        }
    }
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Transaction> {
        return EmptyLayoutViewHolder(LayoutEmptyStateBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = if (transactions.isEmpty()) 1 else transactions.size

    override fun getItemViewType(position: Int): Int = EMPTY_STATE

    override fun onBindViewHolder(
        holder: BaseViewHolder<Transaction>,
        position: Int
    ) {
        holder.bindItem(null)
    }

    inner class EmptyLayoutViewHolder(private val binding: LayoutEmptyStateBinding): BaseViewHolder<Transaction>(binding) {
        override fun bindItem(value: Transaction?) {
            binding.textView8.text = "No transactions!"
        }
    }

    companion object {
        const val EMPTY_STATE = 0
    }
}