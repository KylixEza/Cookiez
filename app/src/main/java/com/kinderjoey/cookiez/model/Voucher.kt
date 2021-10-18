package com.kinderjoey.cookiez.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Voucher(
    val background: String = "",
    val coin: Int = 0,
    val uid: String = "",
    val validUntil: String = "",
    val voucherCategory: String = "",
    val voucherDiscount: Int = 0,
) : Parcelable