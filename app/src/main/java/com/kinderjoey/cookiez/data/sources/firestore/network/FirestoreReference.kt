package com.kinderjoey.cookiez.data.sources.firestore.network

enum class FirestoreReference(val reference: String? = null, val attribute: String? = null) {
    Admin("admin"),
    Menu("menu"),
    Popular("popular"),
    Exclusive("exclusive"),
    All("all"),
    Detail("detail"),
    Ingredients("ingredients"),
    Steps("steps"),
    Review("review"),
    Reviewer("reviewer"),
    Variant("variant"),
    Favorite("favorite"),

    CategoryAttr(attribute = "type"),
    MenuNameAttr(attribute = "menuName"),
    UidAttr(attribute = "uid"),

}