package com.kinderjoey.cookiez.data.sources.dummy

import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.model.Category
import com.kinderjoey.cookiez.model.UserLeaderBoard
import com.kinderjoey.cookiez.util.Constanta

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

    fun setPromotionCoupon(): List<Int> {
        return  listOf(
            R.drawable.promotion_coupon,
            R.drawable.promotion_coupon,
            R.drawable.promotion_coupon
        )
    }

    fun setLeaderboard(): List<UserLeaderBoard> {
        return listOf(
            UserLeaderBoard(
                1,
                Constanta.DUMMY_PROFILE,
                "Annette Black",
                2450
            ),
            UserLeaderBoard(
                2,
                Constanta.DUMMY_PROFILE,
                "Esther Howard",
                2450
            ),
            UserLeaderBoard(
                3,
                Constanta.DUMMY_PROFILE,
                "Eleanor Pena",
                2450
            ),
            UserLeaderBoard(
                4,
                Constanta.DUMMY_PROFILE,
                "Jane Cooper",
                2450
            ),
            UserLeaderBoard(
                5,
                Constanta.DUMMY_PROFILE,
                "Bessie Cooper",
                2450
            ),
            UserLeaderBoard(
                6,
                Constanta.DUMMY_PROFILE,
                "Dianner Russel",
                2450
            ),
            UserLeaderBoard(
                7,
                Constanta.DUMMY_PROFILE,
                "Matthew Natt",
                2450
            ),
            UserLeaderBoard(
                8,
                Constanta.DUMMY_PROFILE,
                "Easter Egg",
                2450
            ),
            UserLeaderBoard(
                9,
                Constanta.DUMMY_PROFILE,
                "Norrand Gold",
                2450
            ),
            UserLeaderBoard(
                10,
                Constanta.DUMMY_PROFILE,
                "Fanny Viola",
                2450
            ),
        )
    }
}