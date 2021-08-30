package com.kinderjoey.demo.ui.voucher.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinderjoey.demo.databinding.ItemListVoucherBinding

class VoucherListAdapter: RecyclerView.Adapter<VoucherListAdapter.VoucherListViewHolder>() {

    private val listOfVoucher = ArrayList<Int>()

    fun setAllData(data: List<Int>) {
        listOfVoucher.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VoucherListAdapter.VoucherListViewHolder {
        val view = ItemListVoucherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VoucherListViewHolder(view)
    }

    override fun onBindViewHolder(holder: VoucherListAdapter.VoucherListViewHolder, position: Int) {
        holder.bind(listOfVoucher[position])
    }

    override fun getItemCount(): Int = listOfVoucher.size

    inner class VoucherListViewHolder(val view: ItemListVoucherBinding): RecyclerView.ViewHolder(view.root) {
        fun bind(voucher: Int) {
            Glide.with(itemView.context)
                .load(voucher)
                .into(view.ivVoucher)
        }
    }
}