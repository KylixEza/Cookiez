package com.kinderjoey.cookiez.data.util

import com.kinderjoey.cookiez.data.sources.firestore.response.*
import com.kinderjoey.cookiez.model.Favorite
import com.kinderjoey.cookiez.model.Variant
import com.kinderjoey.cookiez.model.menu.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object FirestoreMapper {
    fun mapMenuResponsesToDomain(input: List<MenuResponse>): Flow<List<Menu>> {
        val result = ArrayList<Menu>()

        input.map {
            val menu = Menu(
                it.title,
                it.time,
                it.difficulty,
                it.price,
                it.rating,
                it.image,
                it.type,
                it.videoUrl,
                it.description,
                it.estimatedTime,
                it.benefit,

            )
            result.add(menu)
        }
        return flowOf(result)
    }

    fun mapMenuResponToDomain(input: MenuResponse) = flowOf(
        Menu(
            input.title,
            input.time,
            input.difficulty,
            input.price,
            input.rating,
            input.image,
            input.type,
            input.videoUrl,
            input.description,
            input.estimatedTime,
            input.benefit
        )
    )

    fun mapStepResponseToDomain(input: StepResponse) = flowOf(
        Step(
            input.menuName,
            input.steps
        )
    )

    fun mapIngredientResponseToDomain(input: IngredientResponse) = flowOf(
        Ingredient(
            input.menuName,
            input.ingredients
        )
    )

    fun mapReviewResponsesToDomain(input: List<ReviewResponse>): Flow<List<Review>> {
        val result = ArrayList<Review>()

        input.map {
            val review = Review(
                it.imageReviewer,
                it.nameReviewer,
                it.starReviewer
            )
            result.add(review)
        }
        return flowOf(result)
    }

    fun mapVariantResponsesToDomain(input: List<VariantResponse>): Flow<List<Variant>> {
        val result = ArrayList<Variant>()

        input.map {
            val variant = Variant(
                it.menuName,
                it.variant,
                it.composition,
                it.price,
                it.image
            )
            result.add(variant)
        }
        return flowOf(result)
    }

    fun mapFavoriteResponsesToDomain(input: List<FavoriteResponse>): Flow<List<Favorite>> {
        val result = ArrayList<Favorite>()

        input.map {
            val favorite = Favorite(
                it.uid,
                it.title,
                it.time,
                it.difficulty,
                it.price,
                it.rating,
                it.image,
                it.type,
            )
            result.add(favorite)
        }

        return flowOf(result)
    }
}