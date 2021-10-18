package com.kinderjoey.cookiez.data.sources.firestore.response

data class FavoriteResponse(
    val uid: String = "",
    val title: String = "",
    val time: Int = 0,
    val difficulty: String = "",
    val price: Int = 0,
    val rating: Double = 0.0,
    val image: String = "",
    val type: String = "",
)
