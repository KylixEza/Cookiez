package com.kinderjoey.cookiez.data.repository

import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.model.menu.*
import kotlinx.coroutines.flow.Flow

interface ICookiezRepository {

    fun getPopularMenus(): Flow<Resource<List<Menu>>>
    fun getExclusiveMenus(): Flow<Resource<List<Menu>>>
    fun getCategoryMenus(category: String): Flow<Resource<List<Menu>>>
    fun getAllMenus(): Flow<Resource<List<Menu>>>
    fun getDetailMenu(menuName: String): Flow<Resource<DetailMenu>>
    fun getSteps(menuName: String): Flow<Resource<Step>>
    fun getIngredients(menuName: String): Flow<Resource<Ingredient>>
    fun getReviews(menuName: String): Flow<Resource<List<Review>>>
}