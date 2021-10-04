package com.kinderjoey.cookiez.data.sources.firestore

import com.kinderjoey.cookiez.data.sources.firestore.network.FirestoreClient

class FirestoreDataSource(private val firestoreClient: FirestoreClient) {

    suspend fun getPopularMenus() = firestoreClient.getPopularMenus()
    suspend fun getExclusiveMenus() = firestoreClient.getExclusiveMenus()
}