package com.kinderjoey.cookiez.data.sources.firestore.network

enum class FirestoreReference(val reference: String) {
    Admin("admin"),
    Menu("menu"),
    Popular("popular"),
    Exclusive("exclusive")
}