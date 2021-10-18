package com.kinderjoey.cookiez.data.sources.firestore.network

import com.kinderjoey.cookiez.data.sources.firestore.response.*
import com.kinderjoey.cookiez.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FirestoreClient {

    suspend fun getPopularMenus(): Flow<FirestoreResponses<List<MenuResponse>>>
    suspend fun getExclusiveMenus(): Flow<FirestoreResponses<List<MenuResponse>>>
    suspend fun getCategoryMenus(category: String): Flow<FirestoreResponses<List<MenuResponse>>>
    suspend fun getAllMenus(): Flow<FirestoreResponses<List<MenuResponse>>>
    suspend fun getDetailMenu(menuName: String): Flow<FirestoreResponses<MenuResponse?>>
    suspend fun getSteps(menuName: String): Flow<FirestoreResponses<StepResponse?>>
    suspend fun getIngredients(menuName: String): Flow<FirestoreResponses<IngredientResponse?>>
    suspend fun getReviews(menuName: String): Flow<FirestoreResponses<List<ReviewResponse>>>
    suspend fun getVariantMenu(menuName: String): Flow<FirestoreResponses<List<VariantResponse>>>
    suspend fun isFavorite(uid: String, menuName: String): Flow<FirestoreResponses<Boolean>>
    suspend fun addToFavorite(uid: String, menuName: String, data: Favorite): Flow<FirestoreResponses<String>>
    suspend fun removeFromFavorite(uid: String, menuName: String): Flow<FirestoreResponses<String>>
    suspend fun getAllFavorites(uid: String): Flow<FirestoreResponses<List<FavoriteResponse>>>
}