package com.kinderjoey.cookiez.data.repository

import com.kinderjoey.cookiez.data.sources.firestore.FirestoreDataSource
import com.kinderjoey.cookiez.data.sources.firestore.network.FirestoreResponses
import com.kinderjoey.cookiez.data.sources.firestore.response.*
import com.kinderjoey.cookiez.data.util.FirestoreMapper
import com.kinderjoey.cookiez.data.util.FirestoreOnlyResource
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.model.menu.*
import kotlinx.coroutines.flow.Flow

class CookiezRepository(
    private val firestoreDataSource: FirestoreDataSource
): ICookiezRepository {

    override fun getPopularMenus(): Flow<Resource<List<Menu>>> {
        return object : FirestoreOnlyResource<List<Menu>, List<MenuResponse>>() {
            override fun loadFromNetwork(data: List<MenuResponse>): Flow<List<Menu>> {
                return FirestoreMapper.mapMenuResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<List<MenuResponse>>> {
                return firestoreDataSource.getPopularMenus()
            }

        }.asFlow()
    }

    override fun getExclusiveMenus(): Flow<Resource<List<Menu>>> {
        return object : FirestoreOnlyResource<List<Menu>, List<MenuResponse>>() {
            override fun loadFromNetwork(data: List<MenuResponse>): Flow<List<Menu>> {
                return FirestoreMapper.mapMenuResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<List<MenuResponse>>> {
                return firestoreDataSource.getExclusiveMenus()
            }
        }.asFlow()
    }

    override fun getCategoryMenus(category: String): Flow<Resource<List<Menu>>> {
        return object : FirestoreOnlyResource<List<Menu>, List<MenuResponse>>() {
            override fun loadFromNetwork(data: List<MenuResponse>): Flow<List<Menu>> {
                return FirestoreMapper.mapMenuResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<List<MenuResponse>>> {
                return firestoreDataSource.getCategoryMenus(category)
            }
        }.asFlow()
    }

    override fun getAllMenus(): Flow<Resource<List<Menu>>> {
        return object : FirestoreOnlyResource<List<Menu>, List<MenuResponse>>() {
            override fun loadFromNetwork(data: List<MenuResponse>): Flow<List<Menu>> {
                return FirestoreMapper.mapMenuResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<List<MenuResponse>>> {
                return firestoreDataSource.getAllMenus()
            }

        }.asFlow()
    }

    override fun getDetailMenu(menuName: String): Flow<Resource<DetailMenu>> {
        return object : FirestoreOnlyResource<DetailMenu, DetailMenuResponse?>() {
            override fun loadFromNetwork(data: DetailMenuResponse?): Flow<DetailMenu> {
                return FirestoreMapper.mapDetailResponseToDomain(data!!)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<DetailMenuResponse?>> {
                return firestoreDataSource.getDetailMenu(menuName)
            }
        }.asFlow()
    }

    override fun getSteps(menuName: String): Flow<Resource<Step>> {
        return object : FirestoreOnlyResource<Step, StepResponse?>() {
            override fun loadFromNetwork(data: StepResponse?): Flow<Step> {
                return FirestoreMapper.mapStepResponseToDomain(data!!)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<StepResponse?>> {
                return firestoreDataSource.getSteps(menuName)
            }
        }.asFlow()
    }

    override fun getIngredients(menuName: String): Flow<Resource<Ingredient>> {
        return object : FirestoreOnlyResource<Ingredient, IngredientResponse?>() {
            override fun loadFromNetwork(data: IngredientResponse?): Flow<Ingredient> {
                return FirestoreMapper.mapIngredientResponseToDomain(data!!)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<IngredientResponse?>> {
                return firestoreDataSource.getIngredients(menuName)
            }
        }.asFlow()
    }

    override fun getReviews(menuName: String): Flow<Resource<List<Review>>> {
        return object : FirestoreOnlyResource<List<Review>, List<ReviewResponse>>() {
            override fun loadFromNetwork(data: List<ReviewResponse>): Flow<List<Review>> {
                return FirestoreMapper.mapReviewResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<List<ReviewResponse>>> {
                return firestoreDataSource.getReviews(menuName)
            }
        }.asFlow()
    }
}