package com.kinderjoey.cookiez.data.util

import com.kinderjoey.cookiez.data.sources.firestore.network.FirestoreResponses
import kotlinx.coroutines.flow.*

abstract class FirestoreOnlyResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is FirestoreResponses.Success -> {
                emitAll(loadFromNetwork(apiResponse.data).map {
                    Resource.Success(it)
                })
            }

            is FirestoreResponses.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }

            is FirestoreResponses.Empty -> {
                emit(Resource.Empty())
            }
        }
    }

    protected abstract fun loadFromNetwork(data: RequestType): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<FirestoreResponses<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}