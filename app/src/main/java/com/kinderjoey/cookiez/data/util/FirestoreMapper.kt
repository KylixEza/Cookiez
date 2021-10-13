package com.kinderjoey.cookiez.data.util

import com.kinderjoey.cookiez.data.sources.firestore.response.*
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
                it.price,
                it.difficulty,
                it.price,
                it.rating,
                it.image
            )
            result.add(menu)
        }
        return flowOf(result)
    }

    fun mapDetailResponseToDomain(input: DetailMenuResponse) = flowOf(
        DetailMenu(
            input.menuName,
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
}