package com.kinderjoey.cookiez.data.sources.firestore.response

data class MenuResponse(
    val title: String = "",
    val time: Int = 0,
    val difficulty: String = "",
    val price: Int = 0,
    val rating: Double = 0.0,
    val image: String = "",
    val type: String = "",
    val videoUrl: String = "",
    val description: String = "",
    val estimatedTime: String = "",
    val benefit: String = "",
)