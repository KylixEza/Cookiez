package com.kinderjoey.cookiez.model

data class User(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val address: String,
    val xp: Int,
    val coin: Int,
    val cookiezWallet: Int,
    val transaction: Transaction
)

