package com.kinderjoey.cookiez.ui.voucher.screen

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.ItemVoucherBinding
import com.kinderjoey.cookiez.model.Voucher


class VoucherAdapter(private val listItem: ArrayList<Voucher>) :
    RecyclerView.Adapter<VoucherAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding =
            ItemVoucherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemBinding)
    }

    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listItem[position])
    }


    inner class ListViewHolder(private val binding: ItemVoucherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Voucher) {
            with(binding) {
                tvCoin.text = item.coin.toString()
                tvValidUntil.text = item.validUntil
                tvVoucherCategory.text = item.voucherCategory
                tvVoucherDiscount.text = "Discount ${item.voucherDiscount}%"

                when (item.background) {
                    "bg_voucher1" -> layoutTiket.setBackgroundResource(R.drawable.bg_voucher1)
                    "bg_voucher2" -> layoutTiket.setBackgroundResource(R.drawable.bg_voucher2)
                    "bg_voucher3" -> layoutTiket.setBackgroundResource(R.drawable.bg_voucher3)
                }

            }
            binding.btnDetail.setOnClickListener {
                val intent = Intent(itemView.context, DetailVoucherActivity::class.java)
                intent.putExtra(DetailVoucherActivity.VOUCHER, item)
                itemView.context.startActivity(intent)
            }


        }

    }
}