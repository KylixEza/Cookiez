package com.kinderjoey.cookiez.data.sources.firestore.network

sealed class FirestoreResponses<out T> {
    data class Success<out T>(val data: T): FirestoreResponses<T>()
    data class Error(val errorMessage: String?): FirestoreResponses<Nothing>()
}
