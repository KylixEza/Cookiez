package com.kinderjoey.cookiez.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinderjoey.cookiez.databinding.ItemListCategoriesBinding
import com.kinderjoey.cookiez.model.Category
import com.kinderjoey.cookiez.ui.home.HomeFragmentDirections

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
                .load(category.categoryImage)
                .into(view.ivCategory)
            view.tvCategory.text = category.categoryTitle

            itemView.setOnClickListener {
                it.findNavController().navigate(HomeFragmentDirections
                    .actionNavigationHomeToCategoryActivity(category.categoryTitle, category.categoryType))
            }
        }
    }
}