package com.kinderjoey.cookiez.data.repository


import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.model.User
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    fun signUpUser(email: String, password: String, user: User): Flow<Resource<Unit>>
    fun signInUser(email: String, password: String): Flow<Resource<Unit>>
}