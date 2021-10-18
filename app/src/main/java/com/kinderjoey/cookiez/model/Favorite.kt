package com.kinderjoey.cookiez.model

data class Favorite(
    val uid: String,
    val title: String,
    val time: Int,
    val difficulty: String,
    val price: Int,
    val rating: Double,
    val image: String,
    val type: String,
)
