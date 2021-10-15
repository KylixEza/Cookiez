package com.kinderjoey.cookiez.model


data class User(
    val id:String ="",
    val name: String ="",
    val phoneNumber: String = "",
    val email: String ="",
    val address: String ="",
    val xp: Int = 0,
    val coin: Int = 0,
    val cookiezWallet: Int = 0,
    val transaction: List<String> = listOf()
)

