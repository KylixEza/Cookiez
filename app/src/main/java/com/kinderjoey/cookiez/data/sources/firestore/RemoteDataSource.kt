package com.kinderjoey.cookiez.data.sources.firestore

import com.kinderjoey.cookiez.data.sources.firestore.network.FirebaseResponse
import com.kinderjoey.cookiez.data.sources.firestore.network.service.AuthService
import com.kinderjoey.cookiez.data.sources.firestore.network.service.UserService
import com.kinderjoey.cookiez.data.sources.firestore.response.UserResponse
import com.kinderjoey.cookiez.model.User
import kotlinx.coroutines.flow.Flow

class RemoteDataSource(
    private val authService: AuthService,
    private val userService: UserService
) {
    fun signUp(email:String, password:String, user: User) =
        authService.signUp(email,password,user)

    fun signIn(email:String, password:String) =
        authService.signIn(email,password)

    fun getUserByUID(uid: String): Flow<FirebaseResponse<UserResponse>> =
        userService.getUserById(uid)


}