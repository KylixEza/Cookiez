package com.kinderjoey.demo.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinderjoey.demo.databinding.ItemListCategoriesBinding
import com.kinderjoey.demo.model.Category
import com.kinderjoey.demo.ui.category.CategoryActivity

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val listOfCategory = ArrayList<Category>()

    fun setAllData(data: List<Category>) {
        listOfCategory.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = ItemListCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(listOfCategory[position])
    }

    override fun getItemCount(): Int = listOfCategory.size

    inner class CategoryViewHolder(private val view: ItemListCategoriesBinding): RecyclerView.ViewHolder(view.root) {

        fun bind(category: Category) {
            Glide.with(itemView.context)
                .load(category.imageCategory)
                .into(view.ivCategory)
            view.tvCategory.text = category.titleCategory

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, CategoryActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }
    }
}