package com.ayokunlepaul.payee.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayokunlepaul.payee.AutoUpdateRecyclerView
import com.ayokunlepaul.payee.core.base.BaseViewHolder
import com.ayokunlepaul.payee.core.repository.entities.ATMCard
import com.ayokunlepaul.payee.databinding.LayoutPaymentCardFcmbBinding
import com.ayokunlepaul.payee.databinding.LayoutPaymentCardFirstBankBinding
import com.ayokunlepaul.payee.databinding.LayoutPaymentCardGtBankBinding
import com.ayokunlepaul.payee.databinding.LayoutPaymentCardSterlingBankBinding
import kotlin.properties.Delegates

/**
 * Created by ayokunlepaul on 2019-05-28.
 */
class ATMsAdapter(context: Context): RecyclerView.Adapter<BaseViewHolder<ATMCard>>(), AutoUpdateRecyclerView {

    var cards: List<ATMCard> by Delegates.observable(emptyList()) {_, oldCards, newCards ->
        autoNotify(oldCards, newCards) { oldCard, newCard ->
            oldCard.cardNumber == newCard.cardNumber
        }
    }
    private val inflater by lazy { LayoutInflater.from(context) }
    private lateinit var listener: OnCardClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ATMCard> = when (viewType) {
        FIRST_BANK -> FirstBankCardViewHolder(LayoutPaymentCardFirstBankBinding.inflate(inflater, parent, false))
        GTBANK -> GTBankCardViewHolder(LayoutPaymentCardGtBankBinding.inflate(inflater, parent, false))
        STERLING_BANK -> SterlingBankCardViewHolder(LayoutPaymentCardSterlingBankBinding.inflate(inflater, parent, false))
        else -> FCMBCardViewHolder(LayoutPaymentCardFcmbBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = cards.size

    override fun getItemViewType(position: Int): Int = when(cards[position].bankName) {
        "First Bank" -> FIRST_BANK
        "Guarantee Trust Bank" -> GTBANK
        "Sterling Bank" -> STERLING_BANK
        else -> FCMB
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ATMCard>, position: Int) {
        holder.bindItem(cards[position])
        holder.itemView.setOnClickListener {
            listener.onCardClicked(cards[position])
        }
    }

    fun setCardClickListener (listener: () -> OnCardClickListener) {
        this.listener = listener()
    }

    inner class FirstBankCardViewHolder(private val binding: LayoutPaymentCardFirstBankBinding): BaseViewHolder<ATMCard>(binding) {
        override fun bindItem(value: ATMCard?) {
            binding.atmCard = value
        }
    }

    inner class FCMBCardViewHolder(private val binding: LayoutPaymentCardFcmbBinding): BaseViewHolder<ATMCard>(binding) {
        override fun bindItem(value: ATMCard?) {
            binding.atmCard = value
        }
    }

    inner class GTBankCardViewHolder(private val binding: LayoutPaymentCardGtBankBinding): BaseViewHolder<ATMCard>(binding) {
        override fun bindItem(value: ATMCard?) {
            binding.atmCard = value
        }
    }

    inner class SterlingBankCardViewHolder(private val binding: LayoutPaymentCardSterlingBankBinding): BaseViewHolder<ATMCard>(binding) {
        override fun bindItem(value: ATMCard?) {
            binding.atmCard = value
        }
    }

    interface OnCardClickListener {
        fun onCardClicked(card: ATMCard)
    }

    companion object {
        const val FIRST_BANK = 0
        const val GTBANK = 1
        const val STERLING_BANK = 2
        const val FCMB = 3
    }
}