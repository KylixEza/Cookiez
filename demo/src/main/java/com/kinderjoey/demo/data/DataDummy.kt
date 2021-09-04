package com.kinderjoey.demo.data

import com.kinderjoey.demo.R
import com.kinderjoey.demo.model.Category
import com.kinderjoey.demo.model.Menu
import com.kinderjoey.demo.model.Transaction
import com.kinderjoey.demo.model.UserLeaderBoard

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

    fun setLeaderboard(): List<UserLeaderBoard> {
        return listOf(
            UserLeaderBoard(
                1,
                R.drawable.avatar_rank1,
                "Annette Black",
                2450
            ),
            UserLeaderBoard(
                2,
                R.drawable.avatar_rank2,
                "Esther Howard",
                2450
            ),
            UserLeaderBoard(
                3,
                R.drawable.avatar_rank3,
                "Eleanor Pena",
                2450
            ),
            UserLeaderBoard(
                4,
                R.drawable.avatar_rank4,
                "Jane Cooper",
                2450
            ),
            UserLeaderBoard(
                5,
                R.drawable.avatar_rank5,
                "Bessie Cooper",
                2450
            ),
            UserLeaderBoard(
                6,
                R.drawable.avatar_rank6,
                "Dianner Russel",
                2450
            ),
        )
    }

    fun setTransaction(): List<Transaction> {
        return listOf(
            Transaction(
                "Pembayaran",
                "Nasi Goreng Asia (1-3 porsi)",
                "21-08-2021",
                "- Rp. 25000",
                "Berhasil"
            ),
            Transaction(
                "Pembayaran",
                "Nasi Goreng Asia (5-7 porsi)",
                "17-08-2021",
                "- Rp. 75000",
                "Berhasil"
            ),
            Transaction(
                "Pembayaran",
                "Ayam bakar (3-5 porsi)",
                "15-08-2021",
                "- Rp. 41000",
                "Berhasil"
            ),
            Transaction(
                "Pembayaran",
                "Mie rebus (3-5 porsi)",
                "11-08-2021",
                "- Rp. 27000",
                "Berhasil"
            ),
            Transaction(
                "Pembayaran",
                "Nasi lalapan (3-5 porsi)",
                "09-08-2021",
                "- Rp. 32000",
                "Berhasil"
            ),
        )
    }
}