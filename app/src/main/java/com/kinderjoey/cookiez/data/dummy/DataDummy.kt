package com.kinderjoey.cookiez.data.dummy

import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.model.Category

object DataDummy {

    fun setCategories(): List<Category> {
        return listOf<Category>(
            Category(
                R.drawable.ic_all_categories,
                "Semua"
            ),
            Category(
                R.drawable.ic_drinks_category,
                "Minuman"
            ),
            Category(
                R.drawable.ic_vegetable_category,
                "Sayuran"
            ),
            Category(
                R.drawable.ic_soup_catgory,
                "Soup"
            ),
            Category(
                R.drawable.ic_spicy_category,
                "Pedas"
            ),
            Category(
                R.drawable.ic_indoensian_category,
                "Indonesian"
            )
        )
    }
}