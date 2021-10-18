package com.kinderjoey.cookiez.model.menu

data class Menu(
    val title: String,
    val time: Int,
    val difficulty: String,
    val price: Int,
    val rating: Double,
    val image: String,
    val type: String,
    val videoUrl: String,
    val description: String,
    val estimatedTime: String,
    val benefit: String,
)