package com.kinderjoey.demo.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinderjoey.demo.databinding.ItemListCategoriesBinding
import com.kinderjoey.demo.databinding.ItemListPromotionBinding

class PromotionAdapter: RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder>() {

    private val listOfPromotion = ArrayList<Int>()

    fun setAllData(data: List<Int>) {
        listOfPromotion.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PromotionAdapter.PromotionViewHolder {
        val view = ItemListPromotionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PromotionViewHolder(view)
    }

    override fun onBindViewHolder(holder: PromotionAdapter.PromotionViewHolder, position: Int) {
        holder.bind(listOfPromotion[position])
    }

    override fun getItemCount(): Int = listOfPromotion.size

    class PromotionViewHolder(val view: ItemListPromotionBinding): RecyclerView.ViewHolder(view.root) {

        fun bind(promotionCoupon: Int) {
            Glide.with(itemView.context)
                .load(promotionCoupon)
                .into(view.ivPromote)
        }
    }

}