package com.kinderjoey.cookiez.data.sources.firestore.response

data class MenuResponse(
    val title: String,
    val time: String,
    val difficulty: String,
    val price: Int,
    val rating: Int,
    val image: String
)
