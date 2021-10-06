package com.kinderjoey.cookiez.data.repository

import com.kinderjoey.cookiez.data.sources.firestore.FirestoreDataSource
import com.kinderjoey.cookiez.data.sources.firestore.network.FirestoreResponses
import com.kinderjoey.cookiez.data.sources.firestore.response.MenuResponse
import com.kinderjoey.cookiez.data.util.FirestoreMapper
import com.kinderjoey.cookiez.data.util.FirestoreOnlyResource
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.model.Menu
import kotlinx.coroutines.flow.Flow

class CookiezRepository(
    private val firestoreDataSource: FirestoreDataSource
): ICookiezRepository {

    override fun getPopularMenus(): Flow<Resource<List<Menu>>> {
        return object : FirestoreOnlyResource<List<Menu>, List<MenuResponse>>() {
            override fun loadFromNetwork(data: List<MenuResponse>): Flow<List<Menu>> {
                return FirestoreMapper.mapMenusToDomain(data)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<List<MenuResponse>>> {
                return firestoreDataSource.getPopularMenus()
            }

        }.asFlow()
    }

    override fun getExclusiveMenus(): Flow<Resource<List<Menu>>> {
        return object : FirestoreOnlyResource<List<Menu>, List<MenuResponse>>() {
            override fun loadFromNetwork(data: List<MenuResponse>): Flow<List<Menu>> {
                return FirestoreMapper.mapMenusToDomain(data)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<List<MenuResponse>>> {
                return firestoreDataSource.getExclusiveMenus()
            }
        }.asFlow()
    }

    override fun getCategoryMenus(category: String): Flow<Resource<List<Menu>>> {
        return object : FirestoreOnlyResource<List<Menu>, List<MenuResponse>>() {
            override fun loadFromNetwork(data: List<MenuResponse>): Flow<List<Menu>> {
                return FirestoreMapper.mapMenusToDomain(data)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<List<MenuResponse>>> {
                return firestoreDataSource.getCategoryMenus(category)
            }
        }.asFlow()
    }
}