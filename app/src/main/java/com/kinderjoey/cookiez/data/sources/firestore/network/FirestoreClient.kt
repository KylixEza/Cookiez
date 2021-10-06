package com.kinderjoey.cookiez.data.sources.firestore.network

import com.kinderjoey.cookiez.data.sources.firestore.response.MenuResponse
import kotlinx.coroutines.flow.Flow

interface FirestoreClient {

    suspend fun getPopularMenus(): Flow<FirestoreResponses<List<MenuResponse>>>
    suspend fun getExclusiveMenus(): Flow<FirestoreResponses<List<MenuResponse>>>
    suspend fun getCategoryMenus(category: String): Flow<FirestoreResponses<List<MenuResponse>>>
}