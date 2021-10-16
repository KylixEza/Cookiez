package com.kinderjoey.cookiez.data.sources.firestore.network.service

import com.kinderjoey.cookiez.data.sources.firestore.network.FirebaseResponse
import com.kinderjoey.cookiez.data.sources.firestore.network.FirebaseService
import com.kinderjoey.cookiez.data.sources.firestore.response.UserResponse
import com.kinderjoey.cookiez.data.util.FirebaseConstant.FirebaseCollection.USER_COLLECTION
import kotlinx.coroutines.flow.Flow

class UserService : FirebaseService() {
    fun getUserById(id: String): Flow<FirebaseResponse<UserResponse>> =
        getDocument(USER_COLLECTION,id)


}
