package com.kinderjoey.cookiez.data.repository

import com.kinderjoey.cookiez.data.sources.firestore.FirestoreDataSource
import com.kinderjoey.cookiez.data.sources.firestore.network.FirestoreResponses
import com.kinderjoey.cookiez.data.sources.firestore.response.*
import com.kinderjoey.cookiez.data.util.FirestoreMapper
import com.kinderjoey.cookiez.data.util.FirestoreOnlyResource
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.model.Favorite
import com.kinderjoey.cookiez.model.Variant
import com.kinderjoey.cookiez.model.menu.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

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

    override fun getDetailMenu(menuName: String): Flow<Resource<Menu>> {
        return object : FirestoreOnlyResource<Menu, MenuResponse?>() {
            override fun loadFromNetwork(data: MenuResponse?): Flow<Menu> {
                return FirestoreMapper.mapMenuResponToDomain(data!!)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<MenuResponse?>> {
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

    override fun getVariantMenu(menuName: String): Flow<Resource<List<Variant>>> {
        return object : FirestoreOnlyResource<List<Variant>, List<VariantResponse>>() {
            override fun loadFromNetwork(data: List<VariantResponse>): Flow<List<Variant>> {
                return FirestoreMapper.mapVariantResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<List<VariantResponse>>> {
                return firestoreDataSource.getVariantMenu(menuName)
            }
        }.asFlow()
    }

    override fun isFavorite(uid: String, menuName: String): Flow<Resource<Boolean>> {
        return object : FirestoreOnlyResource<Boolean, Boolean>() {
            override fun loadFromNetwork(data: Boolean): Flow<Boolean> {
                return flowOf(data)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<Boolean>> {
                return firestoreDataSource.isFavorite(uid, menuName)
            }
        }.asFlow()
    }

    override fun addToFavorite(
        uid: String,
        menuName: String,
        data: Favorite
    ): Flow<Resource<String>> {
        return object : FirestoreOnlyResource<String, String>() {
            override fun loadFromNetwork(data: String): Flow<String> {
                return flowOf(data)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<String>> {
                return firestoreDataSource.addToFavorite(uid, menuName, data)
            }
        }.asFlow()
    }

    override fun removeFromFavorite(uid: String, menuName: String): Flow<Resource<String>> {
        return object : FirestoreOnlyResource<String, String>() {
            override fun loadFromNetwork(data: String): Flow<String> {
                return flowOf(data)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<String>> {
                return firestoreDataSource.removeFromFavorite(uid, menuName)
            }
        }.asFlow()
    }

    override fun getAllFavorites(uid: String): Flow<Resource<List<Favorite>>> {
        return object : FirestoreOnlyResource<List<Favorite>, List<FavoriteResponse>>() {
            override fun loadFromNetwork(data: List<FavoriteResponse>): Flow<List<Favorite>> {
                return FirestoreMapper.mapFavoriteResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<FirestoreResponses<List<FavoriteResponse>>> {
                return firestoreDataSource.getAllFavorites(uid)
            }
        }.asFlow()
    }
}