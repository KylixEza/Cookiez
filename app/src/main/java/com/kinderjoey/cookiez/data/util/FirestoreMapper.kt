package com.kinderjoey.cookiez.data.util

import com.kinderjoey.cookiez.data.sources.firestore.response.MenuResponse
import com.kinderjoey.cookiez.model.Menu
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object FirestoreMapper {
    fun mapMenusToDomain(input: List<MenuResponse>): Flow<List<Menu>> {
        val result = ArrayList<Menu>()

        input.map {
            val menu = Menu(
                it.title,
                it.price,
                it.difficulty,
                it.price,
                it.rating,
                it.image,
            )
            result.add(menu)
        }
        return flowOf(result)
    }
}