package com.ayokunlepaul.payee.core.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by ayokunlepaul on 2019-05-28.
 */
abstract class BaseViewHolder<in VALUE: Any>(binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {

    abstract fun bindItem(value: VALUE?)
}