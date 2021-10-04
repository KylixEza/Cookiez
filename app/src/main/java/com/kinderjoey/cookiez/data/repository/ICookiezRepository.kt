package com.kinderjoey.cookiez.data.repository

import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.model.Menu
import kotlinx.coroutines.flow.Flow

interface ICookiezRepository {

    fun getPopularMenus(): Flow<Resource<List<Menu>>>
    fun getExclusiveMenus(): Flow<Resource<List<Menu>>>
}