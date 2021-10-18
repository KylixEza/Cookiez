package com.kinderjoey.cookiez.data.sources.firestore

import com.kinderjoey.cookiez.data.sources.firestore.network.FirestoreClient
import com.kinderjoey.cookiez.data.sources.firestore.response.FavoriteResponse
import com.kinderjoey.cookiez.model.Favorite
import com.kinderjoey.cookiez.model.User

class FirestoreDataSource(private val firestoreClient: FirestoreClient) {

    suspend fun getPopularMenus() = firestoreClient.getPopularMenus()
    suspend fun getExclusiveMenus() = firestoreClient.getExclusiveMenus()
    suspend fun getCategoryMenus(category: String) = firestoreClient.getCategoryMenus(category)
    suspend fun getAllMenus() = firestoreClient.getAllMenus()
    suspend fun getDetailMenu(menuName: String) = firestoreClient.getDetailMenu(menuName)
    suspend fun getSteps(menuName: String) = firestoreClient.getSteps(menuName)
    suspend fun getIngredients(menuName: String) = firestoreClient.getIngredients(menuName)
    suspend fun getReviews(menuName: String) = firestoreClient.getReviews(menuName)
    suspend fun getVariantMenu(menuName: String) = firestoreClient.getVariantMenu(menuName)
    suspend fun isFavorite(uid: String, menuName: String) = firestoreClient.isFavorite(uid, menuName)
    suspend fun addToFavorite(uid: String, menuName: String, data: Favorite) =
        firestoreClient.addToFavorite(uid, menuName, data)
    suspend fun removeFromFavorite(uid: String, menuName: String) = firestoreClient.removeFromFavorite(uid, menuName)
    suspend fun getAllFavorites(uid: String) = firestoreClient.getAllFavorites(uid)
}