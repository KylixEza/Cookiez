package com.kinderjoey.cookiez.data.util

import com.kinderjoey.cookiez.data.sources.firestore.network.FirebaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.first

abstract class NetworkBoundRequest<RequestType> {
    private val result: Flow<Resource<Unit>> = flow {
        preRequest()
        emit(Resource.Loading())
        when (val firebaseResponse = createCall().first()) {
            is FirebaseResponse.Success<RequestType> -> {
                saveCallResult(firebaseResponse.data)
                emit(Resource.Success(null))
            }
            is FirebaseResponse.Error -> {
                emit(
                    Resource.Error<Unit>(
                        firebaseResponse.errorMessage
                    )
                )
            }
        }
    } as Flow<Resource<Unit>>

    protected open suspend fun preRequest(){}

    protected abstract suspend fun createCall(): Flow<FirebaseResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)




    fun asFlow() = result
}