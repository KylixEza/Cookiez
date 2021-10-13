package com.kinderjoey.cookiez.data.sources.firestore.response

data class VariantResponse(
    val menuName: String = "",
    val variant: String = "",
    val composition: String = "",
    val price: Int = 0,
    val image: String = ""
)
