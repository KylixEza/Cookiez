package com.kinderjoey.cookiez.data.sources.firestore.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kinderjoey.cookiez.data.sources.firestore.response.MenuResponse
import com.kiwimob.firestore.coroutines.await
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlin.Exception

class FirestoreClientImpl: FirestoreClient {

    private val fireStore: FirebaseFirestore = Firebase.firestore
    private val menuRef = fireStore
        .collection(FirestoreReference.Admin.reference!!)
        .document(FirestoreReference.Menu.reference!!)

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
}