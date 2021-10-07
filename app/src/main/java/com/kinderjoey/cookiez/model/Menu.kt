package com.kinderjoey.cookiez.model

data class Menu(
    val title: String,
    val time: Int,
    val difficulty: String,
    val price: Int,
    val rating: Double,
    val image: String,
    val ingredients: List<String>,
    val steps: List<String>,
    val description: String,
    val estimateTime: String,
    val benefit: String,
    val review: List<Review>

)
