package com.kinderjoey.cookiez.data.sources.firestore

import com.kinderjoey.cookiez.data.sources.firestore.network.FirestoreClient

class FirestoreDataSource(private val firestoreClient: FirestoreClient) {

    suspend fun getPopularMenus() = firestoreClient.getPopularMenus()
    suspend fun getExclusiveMenus() = firestoreClient.getExclusiveMenus()
    suspend fun getCategoryMenus(category: String) = firestoreClient.getCategoryMenus(category)
    suspend fun getAllMenus() = firestoreClient.getAllMenus()
    suspend fun getDetailMenu(menuName: String) = firestoreClient.getDetailMenu(menuName)
    suspend fun getSteps(menuName: String) = firestoreClient.getSteps(menuName)
    suspend fun getIngredients(menuName: String) = firestoreClient.getIngredients(menuName)
    suspend fun getReviews(menuName: String) = firestoreClient.getReviews(menuName)
}