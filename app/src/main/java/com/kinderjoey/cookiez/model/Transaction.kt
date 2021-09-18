package com.kinderjoey.cookiez.model

data class Transaction(
    val status: String,
    val menu: String,
    val timeStamp: String,
    val transactionValue: String,
    val success: String
)
