package com.kinderjoey.cookiez.data.repository



import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.model.User
import kotlinx.coroutines.flow.Flow

interface IProfileRepository {
    fun getUserProfile(uid: String): Flow<Resource<User>>

}