package com.kinderjoey.cookiez.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Variant(
    val menuName: String,
    val variant: String,
    val composition: String,
    val price: Int,
    val image: String
): Parcelable
