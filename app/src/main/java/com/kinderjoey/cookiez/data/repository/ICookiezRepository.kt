package com.kinderjoey.cookiez.data.repository

import com.kinderjoey.cookiez.data.sources.firestore.network.FirestoreResponses
import com.kinderjoey.cookiez.data.sources.firestore.response.FavoriteResponse
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.model.Favorite
import com.kinderjoey.cookiez.model.Variant
import com.kinderjoey.cookiez.model.menu.*
import kotlinx.coroutines.flow.Flow

interface ICookiezRepository {

    fun getPopularMenus(): Flow<Resource<List<Menu>>>
    fun getExclusiveMenus(): Flow<Resource<List<Menu>>>
    fun getCategoryMenus(category: String): Flow<Resource<List<Menu>>>
    fun getAllMenus(): Flow<Resource<List<Menu>>>
    fun getDetailMenu(menuName: String): Flow<Resource<Menu>>
    fun getSteps(menuName: String): Flow<Resource<Step>>
    fun getIngredients(menuName: String): Flow<Resource<Ingredient>>
    fun getReviews(menuName: String): Flow<Resource<List<Review>>>
    fun getVariantMenu(menuName: String): Flow<Resource<List<Variant>>>
    fun isFavorite(uid: String, menuName: String): Flow<Resource<Boolean>>
    fun addToFavorite(uid: String, menuName: String, data: Favorite): Flow<Resource<String>>
    fun removeFromFavorite(uid: String, menuName: String): Flow<Resource<String>>
    fun getAllFavorites(uid: String): Flow<Resource<List<Favorite>>>
}