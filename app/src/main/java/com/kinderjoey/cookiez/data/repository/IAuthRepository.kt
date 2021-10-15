package com.kinderjoey.cookiez.data.repository


import com.kinderjoey.cookiez.data.mapper.toEntity
import com.kinderjoey.cookiez.data.sources.firestore.RemoteDataSource
import com.kinderjoey.cookiez.data.sources.firestore.network.FirebaseResponse
import com.kinderjoey.cookiez.data.sources.firestore.response.UserResponse
import com.kinderjoey.cookiez.data.sources.local.LocalDataSource
import com.kinderjoey.cookiez.data.util.NetworkBoundRequest
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.model.User
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
     fun signUpUser(email: String, password: String, user: User): Flow<Resource<Unit>>
    fun signInUser(email: String, password: String): Flow<Resource<Unit>>
}