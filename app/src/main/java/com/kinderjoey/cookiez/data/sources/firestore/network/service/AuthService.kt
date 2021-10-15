package com.kinderjoey.cookiez.data.sources.firestore.network.service

import com.kinderjoey.cookiez.data.sources.firestore.network.FirebaseResponse
import com.kinderjoey.cookiez.data.sources.firestore.network.FirebaseService
import com.kinderjoey.cookiez.data.sources.firestore.response.UserResponse
import com.kinderjoey.cookiez.data.util.FirebaseConstant
import com.kinderjoey.cookiez.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class AuthService : FirebaseService() {

    fun signUp(email: String, password: String, user: User): Flow<FirebaseResponse<UserResponse>> =
        flow {
            createUserWithEmailAndPassword(email, password).collect { response ->
                when (response) {
                    is FirebaseResponse.Success -> {
                        emitAll(
                            setDocument<User, UserResponse>(
                                FirebaseConstant.FirebaseCollection.USER_COLLECTION,
                                response.data,
                                user.copy(
                                    id = response.data
                                )
                            )
                        )
                    }
                    is FirebaseResponse.Error -> {
                        emit(FirebaseResponse.Error(response.errorMessage))
                    }
                    FirebaseResponse.Empty -> {
                        emit(FirebaseResponse.Empty)
                    }
                }

            }
        }

    fun signIn(email: String, password: String): Flow<FirebaseResponse<UserResponse>> =
        flow {
            signInWithEmailAndPassword(email, password).collect { response ->
                when (response) {
                    is FirebaseResponse.Success -> {
                        emitAll(
                            getDocument<UserResponse>(
                                FirebaseConstant.FirebaseCollection.USER_COLLECTION,
                                response.data
                            )
                        )
                    }
                    is FirebaseResponse.Error -> {
                        emit(FirebaseResponse.Error(response.errorMessage))
                    }
                    FirebaseResponse.Empty -> {
                        emit(FirebaseResponse.Empty)
                    }
                }

            }
        }
}