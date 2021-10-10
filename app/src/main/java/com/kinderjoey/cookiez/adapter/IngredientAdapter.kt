package com.kinderjoey.cookiez.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kinderjoey.cookiez.databinding.ItemListIngredientBinding

class IngredientAdapter: RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    private val listOfIngredient = ArrayList<String>()

    fun setAllData(data: List<String>) {
        listOfIngredient.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfIngredient[position])
    }

    override fun getItemCount(): Int = listOfIngredient.size

    inner class ViewHolder(val view: ItemListIngredientBinding): RecyclerView.ViewHolder(view.root) {
        fun bind(ingredient: String) {
            view.apply {
                tvIngredient.text = ingredient
            }
        }
    }
}