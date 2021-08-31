package com.kinderjoey.demo.data

import com.kinderjoey.demo.R
import com.kinderjoey.demo.model.Category
import com.kinderjoey.demo.model.Menu

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

    fun setPopularMenu(): List<Menu> {
        return listOf(
            Menu(
                "Nasi Goreng Asia",
                "15 Menit",
                "Mudah",
                "Rp. 15000",
                "4.6",
                R.drawable.nasi_goreng_asia
            ),
            Menu(
                "Fried Chicken Cool",
                "15 Menit",
                "Menengah",
                "Rp. 15000",
                "4.6",
                R.drawable.fried_chicken_cool
            ),
            Menu(
                "Green Soup",
                "15 Menit",
                "Sulit",
                "Rp. 15000",
                "4.6",
                R.drawable.green_soup
            )
        )
    }

    fun setExclusive(): List<Menu> {
        return listOf(
            Menu(
                "Italian Pizza",
                "15 Menit",
                "Mudah",
                "Rp. 15000",
                "4.6",
                R.drawable.italian_pizza
            ),
            Menu(
                "Nasi Goreng Asia",
                "15 Menit",
                "Mudah",
                "Rp. 15000",
                "4.6",
                R.drawable.nasi_goreng_asia
            ),
            Menu(
                "Salad",
                "15 Menit",
                "Menengah",
                "Rp. 15000",
                "4.6",
                R.drawable.salad
            )
        )
    }

    fun setAllCategories(): List<Menu> {
        return listOf(
            Menu(
                "Nasi Goreng Asia",
                "15 Menit",
                "Mudah",
                "Rp. 15000",
                "4.6",
                R.drawable.nasi_goreng_asia
            ),
            Menu(
                "Fried Chicken Cool",
                "15 Menit",
                "Menengah",
                "Rp. 15000",
                "4.6",
                R.drawable.fried_chicken_cool
            ),
            Menu(
                "Nasi Goreng Jawa",
                "15 Menit",
                "Sulit",
                "Rp. 15000",
                "4.6",
                R.drawable.nasi_goreng_asia
            ),
            Menu(
                "Italian Pizza",
                "15 Menit",
                "Mudah",
                "Rp. 15000",
                "4.6",
                R.drawable.italian_pizza
            ),
            Menu(
                "Salad",
                "15 Menit",
                "Menengah",
                "Rp. 15000",
                "4.6",
                R.drawable.salad
            )
        )
    }

    fun setAvailableVoucher(): List<Int> {
        return arrayListOf(
            R.drawable.voucher_red,
            R.drawable.voucher_yellow,
            R.drawable.voucher_orange,
            R.drawable.voucher_red,
            R.drawable.voucher_yellow,
            R.drawable.voucher_orange,
            R.drawable.voucher_red
        )
    }

    fun setOwnVoucher(): List<Int> {
        return arrayListOf(
            R.drawable.voucher_red,
            R.drawable.voucher_yellow,
        )
    }

    fun setLeaderboard(): List<Int> {
        return listOf(
            R.drawable.leaderboard_first,
            R.drawable.leaderboard_second,
            R.drawable.leaderboard_third,
            R.drawable.leaderboard_fourth,
            R.drawable.leaderboard_fifth,
            R.drawable.leaderboard_sixth
        )
    }
}