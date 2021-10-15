package com.kinderjoey.cookiez.data.sources.firestore.network

import com.kinderjoey.cookiez.data.sources.firestore.response.*
import com.kinderjoey.cookiez.model.User
import kotlinx.coroutines.flow.Flow

interface FirestoreClient {

    suspend fun getPopularMenus(): Flow<FirestoreResponses<List<MenuResponse>>>
    suspend fun getExclusiveMenus(): Flow<FirestoreResponses<List<MenuResponse>>>
    suspend fun getCategoryMenus(category: String): Flow<FirestoreResponses<List<MenuResponse>>>
    suspend fun getAllMenus(): Flow<FirestoreResponses<List<MenuResponse>>>
    suspend fun getDetailMenu(menuName: String): Flow<FirestoreResponses<DetailMenuResponse?>>
    suspend fun getSteps(menuName: String): Flow<FirestoreResponses<StepResponse?>>
    suspend fun getIngredients(menuName: String): Flow<FirestoreResponses<IngredientResponse?>>
    suspend fun getReviews(menuName: String): Flow<FirestoreResponses<List<ReviewResponse>>>
    suspend fun getVariantMenu(menuName: String): Flow<FirestoreResponses<List<VariantResponse>>>
}