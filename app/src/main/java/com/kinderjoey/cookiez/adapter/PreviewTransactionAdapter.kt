package com.kinderjoey.cookiez.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kinderjoey.cookiez.databinding.ItemListLastTransactionBinding
import com.kinderjoey.cookiez.model.Transaction

class PreviewTransactionAdapter:
    RecyclerView.Adapter<PreviewTransactionAdapter.PreviewTransactionViewHolder>() {

    private val listOfTransaction = ArrayList<Transaction>()

    fun setAllData(data: List<Transaction>) {
        listOfTransaction.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PreviewTransactionAdapter.PreviewTransactionViewHolder {
        val view = ItemListLastTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PreviewTransactionViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PreviewTransactionAdapter.PreviewTransactionViewHolder,
        position: Int
    ) {
        holder.bind(listOfTransaction[position])
    }

    override fun getItemCount(): Int = listOfTransaction.size

    inner class PreviewTransactionViewHolder(val view: ItemListLastTransactionBinding): RecyclerView.ViewHolder(view.root) {
        fun bind(transaction: Transaction) {
            view.apply {
                tvStatus.text = transaction.status
                tvMenu.text = transaction.menu
                tvTimeStamp.text = transaction.timeStamp
                tvTransactionValue.text = transaction.transactionValue
                tvSuccess.text = transaction.success
            }
        }

    }
}