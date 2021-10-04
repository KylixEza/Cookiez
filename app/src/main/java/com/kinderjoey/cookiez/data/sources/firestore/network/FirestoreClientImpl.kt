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
import kotlinx.coroutines.launch
import java.lang.Exception

class FirestoreClientImpl: FirestoreClient {

    private val fireStore: FirebaseFirestore = Firebase.firestore
    private val menuRef = fireStore
        .collection(FirestoreReference.Admin.reference)
        .document(FirestoreReference.Menu.reference)

    override suspend fun getPopularMenus(): Flow<FirestoreResponses<List<MenuResponse>>> = flow {
        try {
            var listOfPopularMenu: List<MenuResponse> = ArrayList()

            CoroutineScope(Dispatchers.IO).launch {
                listOfPopularMenu = menuRef
                    .collection(FirestoreReference.Popular.reference)
                    .get()
                    .await()
                    .toObjects(MenuResponse::class.java)
            }.join()

            if (listOfPopularMenu.isNullOrEmpty())
                emit(FirestoreResponses.Error(null))
            else
                emit(FirestoreResponses.Success(listOfPopularMenu))

        } catch (e: Exception) {
            emit(FirestoreResponses.Error(e.message))
        }
    }

    override suspend fun getExclusiveMenus(): Flow<FirestoreResponses<List<MenuResponse>>> = flow {
        try {
            var listOfExclusiveMenu: List<MenuResponse> = ArrayList()

            CoroutineScope(Dispatchers.IO).launch {
                listOfExclusiveMenu = menuRef
                    .collection(FirestoreReference.Exclusive.reference)
                    .get()
                    .await()
                    .toObjects(MenuResponse::class.java)
            }

            if (listOfExclusiveMenu.isNullOrEmpty())
                emit(FirestoreResponses.Error(null))
            else
                emit(FirestoreResponses.Success(listOfExclusiveMenu))

        } catch (e: Exception) {
            emit(FirestoreResponses.Error(null))
        }
    }
}