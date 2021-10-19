package com.kinderjoey.cookiez.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinderjoey.cookiez.databinding.ItemListCategoriesBinding
import com.kinderjoey.cookiez.databinding.ItemTopUpPaymentBinding
import com.kinderjoey.cookiez.model.Category
import com.kinderjoey.cookiez.model.TopUp
import com.kinderjoey.cookiez.ui.home.HomeFragmentDirections
import com.kinderjoey.cookiez.ui.profile.wallet.DetailTopUpActivity

class TopUpAdapter : RecyclerView.Adapter<TopUpAdapter.TopUpViewHolder>() {

    private val listOfCategory = ArrayList<TopUp>()

    fun setAllData(data: List<TopUp>) {
        listOfCategory.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopUpViewHolder {
        val view =
            ItemTopUpPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopUpViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopUpViewHolder, position: Int) {
        holder.bind(listOfCategory[position])
    }

    override fun getItemCount(): Int = listOfCategory.size

    inner class TopUpViewHolder(private val view: ItemTopUpPaymentBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(item: TopUp) {
            Glide.with(itemView.context)
                .load(item.icon)
                .into(view.ivPaymentLogo)

            view.tvPaymentName.text = item.name

            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(
                        itemView.context,
                        DetailTopUpActivity::class.java
                    ).putExtra("TopUpEntity", item.name)
                )
            }
        }
    }
}