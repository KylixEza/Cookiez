package com.kinderjoey.cookiez.data.sources.dummy

import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.model.Category
import com.kinderjoey.cookiez.model.TopUp
import com.kinderjoey.cookiez.model.Transaction
import com.kinderjoey.cookiez.model.UserLeaderBoard
import com.kinderjoey.cookiez.util.CategoryType
import com.kinderjoey.cookiez.util.Constanta

object DataDummy {

    fun setCategories(): List<Category> {
        return listOf(
            Category(
                R.drawable.ic_all_categories,
                CategoryType.All.title,
                CategoryType.All.type
            ),
            Category(
                R.drawable.ic_drinks_category,
                CategoryType.Drink.title,
                CategoryType.Drink.type
            ),
            Category(
                R.drawable.ic_vegetable_category,
                CategoryType.Vegetable.title,
                CategoryType.Vegetable.type
            ),
            Category(
                R.drawable.ic_soup_catgory,
                CategoryType.Soup.title,
                CategoryType.Soup.type
            ),
            Category(
                R.drawable.ic_spicy_category,
                CategoryType.Spicy.title,
                CategoryType.Spicy.type
            ),
            Category(
                R.drawable.ic_indoensian_category,
                CategoryType.Indonesian.title,
                CategoryType.Indonesian.type
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

    fun setTopUpPayment(): List<TopUp>{
        return listOf(
            TopUp("BCA",R.drawable.ic_logo_bca),
            TopUp("BNI",R.drawable.ic_logo_bni),
            TopUp("BRI",R.drawable.ic_logo_bri),
            TopUp("Dana",R.drawable.ic_logo_dana),
            TopUp("Gopay",R.drawable.ic_logo_gopay),
            TopUp("LinkAja",R.drawable.ic_logo_link_aja),
            TopUp("Ovo",R.drawable.ic_logo_ovo),
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