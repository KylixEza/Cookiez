package com.kinderjoey.cookiez.data.sources.firestore.response


data class UserResponse(
    val uid: String = "",
    val name: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val address: String = "",
    val xp: Int = 0,
    val coin: Int = 0,
    val cookiezWallet: Int = 0,
    val transaction: List<String>? = listOf(),
    val avatar: String = ""
)
