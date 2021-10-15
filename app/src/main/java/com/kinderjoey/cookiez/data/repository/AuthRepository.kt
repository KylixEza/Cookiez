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

class AuthRepository(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) : IAuthRepository {
    override fun signUpUser(email: String, password: String, user: User): Flow<Resource<Unit>> =
        object : NetworkBoundRequest<UserResponse>() {

            override suspend fun createCall(): Flow<FirebaseResponse<UserResponse>> =
                remote.signUp(email,password,user)

            override suspend fun saveCallResult(data: UserResponse) =
                local.insertUser(data.toEntity())

        }.asFlow()

    override fun signInUser(email: String, password: String): Flow<Resource<Unit>> =
        object : NetworkBoundRequest<UserResponse>() {

            override suspend fun createCall(): Flow<FirebaseResponse<UserResponse>> =
                remote.signIn(email,password)

            override suspend fun saveCallResult(data: UserResponse) =
                local.insertUser(data.toEntity())

        }.asFlow()
}