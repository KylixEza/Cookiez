package com.kinderjoey.cookiez.data.sources.firestore.network

import android.util.Log
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kinderjoey.cookiez.data.sources.firestore.response.*
import com.kinderjoey.cookiez.model.Favorite
import com.kiwimob.firestore.coroutines.await
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.Exception

class FirestoreClientImpl: FirestoreClient {

    private val fireStore: FirebaseFirestore = Firebase.firestore
    private val menuRef = fireStore
        .collection(FirestoreReference.Admin.reference!!)
        .document(FirestoreReference.Menu.reference!!)

    private val userRef = fireStore
        .collection(FirestoreReference.User.reference!!)

    override suspend fun getPopularMenus(): Flow<FirestoreResponses<List<MenuResponse>>> = flow {
        var listOfPopularMenu: List<MenuResponse> = ArrayList()

        try {
            CoroutineScope(Dispatchers.IO).launch {
                listOfPopularMenu = menuRef
                    .collection(FirestoreReference.Popular.reference.toString())
                    .get()
                    .await()
                    .toObjects(MenuResponse::class.java)
            }.join()
        } catch (e: Exception) {
            emit(FirestoreResponses.Error(e.localizedMessage))
        }

        Log.e("Popular Menus", listOfPopularMenu.toString())

        if (listOfPopularMenu.isNullOrEmpty())
            emit(FirestoreResponses.Empty())
        else
            emit(FirestoreResponses.Success(listOfPopularMenu))

    }.flowOn(Dispatchers.IO)

    override suspend fun getExclusiveMenus(): Flow<FirestoreResponses<List<MenuResponse>>> = flow {
        var listOfExclusiveMenu: List<MenuResponse> = ArrayList()

        try {
            CoroutineScope(Dispatchers.IO).launch {
                listOfExclusiveMenu = menuRef
                    .collection(FirestoreReference.Exclusive.reference.toString())
                    .get()
                    .await()
                    .toObjects(MenuResponse::class.java)
            }.join()
        } catch (e: Exception) {
            emit(FirestoreResponses.Error(e.localizedMessage))
        }

        if (listOfExclusiveMenu.isNullOrEmpty())
            emit(FirestoreResponses.Empty())
        else
            emit(FirestoreResponses.Success(listOfExclusiveMenu))

    }.flowOn(Dispatchers.IO)

    override suspend fun getCategoryMenus(category: String): Flow<FirestoreResponses<List<MenuResponse>>> = flow {
        var listOfCategory: List<MenuResponse> = ArrayList()

        try {
            CoroutineScope(Dispatchers.IO).launch {
                listOfCategory = menuRef
                    .collection(FirestoreReference.All.reference.toString())
                    .whereEqualTo(FirestoreReference.CategoryAttr.attribute!!, category)
                    .get()
                    .await()
                    .toObjects(MenuResponse::class.java)
            }.join()
        } catch (e: Exception) {
            emit(FirestoreResponses.Error(e.localizedMessage))
        }

        if (listOfCategory.isNullOrEmpty())
            emit(FirestoreResponses.Empty())
        else
            emit(FirestoreResponses.Success(listOfCategory))
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllMenus(): Flow<FirestoreResponses<List<MenuResponse>>> = flow {
        var listOfAll: List<MenuResponse> = ArrayList()

        try {
            CoroutineScope(Dispatchers.IO).launch {
                listOfAll = menuRef
                    .collection(FirestoreReference.All.reference.toString())
                    .get()
                    .await()
                    .toObjects(MenuResponse::class.java)
            }.join()
        } catch (e: Exception) {
            emit(FirestoreResponses.Error(e.localizedMessage))
        }

        if (listOfAll.isNullOrEmpty())
            emit(FirestoreResponses.Empty())
        else
            emit(FirestoreResponses.Success(listOfAll))
    }.flowOn(Dispatchers.IO)

    override suspend fun getDetailMenu(menuName: String): Flow<FirestoreResponses<MenuResponse?>> = flow {
        var detailMenu: MenuResponse? = null

        try {
            CoroutineScope(Dispatchers.IO).launch {
                detailMenu = menuRef
                    .collection(FirestoreReference.All.reference.toString())
                    .document(menuName)
                    .get()
                    .await()
                    .toObject(MenuResponse::class.java)!!
            }.join()
        } catch (e: Exception) {
            emit(FirestoreResponses.Error(e.localizedMessage))
        }

        emit(FirestoreResponses.Success(detailMenu))

    }.flowOn(Dispatchers.IO)

    override suspend fun getSteps(menuName: String): Flow<FirestoreResponses<StepResponse?>> = flow {
        var steps: StepResponse? = null

        try {
            CoroutineScope(Dispatchers.IO).launch {
                steps = menuRef
                    .collection(FirestoreReference.Steps.reference.toString())
                    .document(menuName)
                    .get()
                    .await()
                    .toObject(StepResponse::class.java)
            }.join()
        } catch (e: Exception) {
            emit(FirestoreResponses.Error(e.localizedMessage))
        }

        if (steps?.steps.isNullOrEmpty())
            emit(FirestoreResponses.Empty())
        else
            emit(FirestoreResponses.Success(steps))

    }.flowOn(Dispatchers.IO)

    override suspend fun getIngredients(menuName: String): Flow<FirestoreResponses<IngredientResponse?>> = flow {
        var ingredients: IngredientResponse? = null

        try {
            CoroutineScope(Dispatchers.IO).launch {
                ingredients = menuRef
                    .collection(FirestoreReference.Ingredients.reference.toString())
                    .document(menuName)
                    .get()
                    .await()
                    .toObject(IngredientResponse::class.java)
            }.join()
        } catch (e: Exception) {
            emit(FirestoreResponses.Error(e.localizedMessage))
        }

        if (ingredients?.ingredients.isNullOrEmpty())
            emit(FirestoreResponses.Empty())
        else
            emit(FirestoreResponses.Success(ingredients))

    }.flowOn(Dispatchers.IO)

    override suspend fun getReviews(menuName: String): Flow<FirestoreResponses<List<ReviewResponse>>> = flow {
        var listOfReviews: List<ReviewResponse> = ArrayList()

        try {
            CoroutineScope(Dispatchers.IO).launch {
                listOfReviews = menuRef
                    .collection(FirestoreReference.Review.reference.toString())
                    .document(menuName)
                    .collection(FirestoreReference.Reviewer.reference.toString())
                    .get()
                    .await()
                    .toObjects(ReviewResponse::class.java)
            }.join()
        } catch (e: Exception) {
            emit(FirestoreResponses.Error(e.localizedMessage))
        }

        if (listOfReviews.isNullOrEmpty())
            emit(FirestoreResponses.Empty())
        else
            emit(FirestoreResponses.Success(listOfReviews))
    }.flowOn(Dispatchers.IO)

    override suspend fun getVariantMenu(menuName: String): Flow<FirestoreResponses<List<VariantResponse>>> = flow {
        var listOfVariants: List<VariantResponse> = ArrayList()

        try {
            CoroutineScope(Dispatchers.IO).launch {
                listOfVariants = menuRef
                    .collection(FirestoreReference.Variant.reference.toString())
                    .whereEqualTo(FirestoreReference.MenuNameAttr.attribute.toString(), menuName)
                    .get()
                    .await()
                    .toObjects(VariantResponse::class.java)
            }.join()
        } catch (e: Exception) {
            emit(FirestoreResponses.Error(e.localizedMessage))
        }

        if (listOfVariants.isNullOrEmpty())
            emit(FirestoreResponses.Empty())
        else
            emit(FirestoreResponses.Success(listOfVariants))
    }.flowOn(Dispatchers.IO)

    private suspend fun isFavoriteExist(uid: String, menuName: String): Boolean {
        return userRef
            .document(uid)
            .collection(FirestoreReference.Favorite.reference.toString())
            .document(menuName)
            .get()
            .await()
            .exists()
    }

    override suspend fun isFavorite(
        uid: String,
        menuName: String
    ): Flow<FirestoreResponses<Boolean>>  = flow {
        var isFavoriteExist = false

        try {
            CoroutineScope(Dispatchers.IO).launch {
                isFavoriteExist = isFavoriteExist(uid, menuName)
            }.join()

        } catch (e: Exception) {
            emit(FirestoreResponses.Success(false))
        }

        if (isFavoriteExist)
            emit(FirestoreResponses.Success(true))
        else
            emit(FirestoreResponses.Success(false))

    }.flowOn(Dispatchers.IO)

    override suspend fun addToFavorite(
        uid: String,
        menuName: String,
        data: Favorite
    ): Flow<FirestoreResponses<String>>  = flow {
        CoroutineScope(Dispatchers.IO).launch {
            userRef
                .document(uid)
                .collection(FirestoreReference.Favorite.reference.toString())
                .document(menuName)
                .set(data)
                .await()
        }.join()

        emit(FirestoreResponses.Success(""))
    }

    override suspend fun removeFromFavorite(
        uid: String,
        menuName: String
    ): Flow<FirestoreResponses<String>> = flow {
        CoroutineScope(Dispatchers.IO).launch {
            userRef
                .document(uid)
                .collection(FirestoreReference.Favorite.reference.toString())
                .document(menuName)
                .delete()
                .await()
        }.join()

        emit(FirestoreResponses.Success(""))
    }

    @DelicateCoroutinesApi
    override suspend fun getAllFavorites(uid: String): Flow<FirestoreResponses<List<FavoriteResponse>>> = flow {
        var listOfFavorite: List<FavoriteResponse> = ArrayList()

        try {
            GlobalScope.launch {
                listOfFavorite = userRef
                    .document(uid)
                    .collection(FirestoreReference.Favorite.reference.toString())
                    .get()
                    .await()
                    .toObjects(FavoriteResponse::class.java)
            }.join()
        } catch (e: Exception) {
            emit(FirestoreResponses.Error(e.message))
        }

        if (listOfFavorite.isNullOrEmpty())
            emit(FirestoreResponses.Empty())
        else
            emit(FirestoreResponses.Success(listOfFavorite))
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllSearches(query: String): Flow<FirestoreResponses<List<MenuResponse>>> = flow {
        var listOfSearch: List<MenuResponse> = ArrayList()

        try {
            CoroutineScope(Dispatchers.IO).launch {
                listOfSearch = menuRef
                    .collection(FirestoreReference.All.reference.toString())
                    .whereArrayContains(FirestoreReference.TitleAttr.attribute.toString(), query)
                    .get()
                    .await()
                    .toObjects(MenuResponse::class.java)
            }.join()
        } catch (e: Exception) {
            emit(FirestoreResponses.Error(e.message))
        }

        if (listOfSearch.isNullOrEmpty())
            emit(FirestoreResponses.Empty())
        else
            emit(FirestoreResponses.Success(listOfSearch))
    }
}