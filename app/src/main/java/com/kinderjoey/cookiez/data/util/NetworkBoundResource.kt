package com.kinderjoey.cookiez.data.util

import com.kinderjoey.cookiez.data.sources.firestore.network.FirestoreResponses
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {
        val dbSource = loadFromDB().first()
        emit(Resource.Loading(dbSource))
        if (shouldFetch(dbSource)) {
            when (val firebaseResponse = createCall().first()) {
                is FirestoreResponses.Success<RequestType> -> {
                    saveCallResult(firebaseResponse.data)
                    emitAll(loadFromDB().map {
                        Resource.Success(
                            it
                        )
                    })
                }
                is FirestoreResponses.Empty -> {
                    emitAll(loadFromDB().map {
                        Resource.Success(
                            it
                        )
                    })
                }
                is FirestoreResponses.Error -> {
                    onFetchFailed()
                    emit(
                        Resource.Error<ResultType>(
                            firebaseResponse.errorMessage
                        )
                    )
                }
            }
        } else {
            emitAll(loadFromDB().map {
                Resource.Success(it)
            })
        }
    }

    protected abstract fun loadFromDB(): Flow<ResultType?>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<FirestoreResponses<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    protected open fun onFetchFailed() {}

    fun asFlow() = result

}