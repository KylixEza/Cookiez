package com.kinderjoey.cookiez.data.sources.firestore.response

data class IngredientResponse(
    val menuName: String = "",
    val ingredients: ArrayList<String> = ArrayList<String>()
)
