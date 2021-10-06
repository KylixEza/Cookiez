package com.kinderjoey.cookiez.data.sources.firestore.network

enum class FirestoreReference(val reference: String? = null, val attribute: String? = null) {
    Admin("admin"),
    Menu("menu"),
    Popular("popular"),
    Exclusive("exclusive"),
    All("all"),
    CategoryAttr(attribute = "category")
}