package com.kinderjoey.cookiez.data.repository


import com.kinderjoey.cookiez.data.mapper.toEntity
import com.kinderjoey.cookiez.data.mapper.toFlowModel
import com.kinderjoey.cookiez.data.sources.firestore.RemoteDataSource
import com.kinderjoey.cookiez.data.sources.firestore.network.FirebaseResponse
import com.kinderjoey.cookiez.data.sources.firestore.network.FirestoreResponses
import com.kinderjoey.cookiez.data.sources.firestore.response.UserResponse
import com.kinderjoey.cookiez.data.sources.local.LocalDataSource
import com.kinderjoey.cookiez.data.util.NetworkBoundRequest
import com.kinderjoey.cookiez.data.util.NetworkBoundResource
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.model.User
import kotlinx.coroutines.flow.Flow

class ProfileRepository(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) : IProfileRepository {

    override fun getUserProfile(uid: String): Flow<Resource<User>> =
        object : NetworkBoundResource<User, UserResponse>(){
            override fun loadFromDB(): Flow<User?> =
                local.selectUserByUid(uid).toFlowModel()

            override fun shouldFetch(data: User?): Boolean =
                data == null

            override suspend fun createCall(): Flow<FirebaseResponse<UserResponse>> =
                remote.getUserByUID(uid)

            override suspend fun saveCallResult(data: UserResponse) =
                local.insertUser(data.toEntity())
        }.asFlow()

}