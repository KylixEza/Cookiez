package com.kinderjoey.cookiez.data.sources.firestore.network

import android.util.Log
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
            emit(FirestoreResponses.Error(null))
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
            emit(FirestoreResponses.Error(null))
        else
            emit(FirestoreResponses.Success(listOfExclusiveMenu))

    }.flowOn(Dispatchers.IO)

    override suspend fun getCategoryMenus(category: String): Flow<FirestoreResponses<List<MenuResponse>>> = flow {
            var listOfCategory: List<MenuResponse> = ArrayList()

            CoroutineScope(Dispatchers.IO).launch {
                listOfCategory = menuRef
                    .collection(FirestoreReference.All.reference.toString())
                    .whereEqualTo(FirestoreReference.CategoryAttr.attribute!!, category)
                    .get()
                    .await()
                    .toObjects(MenuResponse::class.java)
            }.join()

            if (listOfCategory.isNullOrEmpty())
                emit(FirestoreResponses.Error(null))
            else
                emit(FirestoreResponses.Success(listOfCategory))
    }.flowOn(Dispatchers.IO)
}