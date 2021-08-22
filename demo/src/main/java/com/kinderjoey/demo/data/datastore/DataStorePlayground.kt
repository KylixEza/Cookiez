package com.kinderjoey.demo.data.datastore

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DataStorePlayground(private val context: Context) {

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var mInstance: DataStorePlayground? = null

        fun getInstance(context: Context): DataStorePlayground =
            mInstance?: synchronized(this) {
                val newInstance = mInstance?: DataStorePlayground(context).also { mInstance = it }
                newInstance
            }
    }

    private val Context.userPreferenceDataStore: DataStore<Preferences> by preferencesDataStore(
        name = DataStoreUtil.DATA_STORE_NAME
    )

    suspend fun saveToDataStore(name: String) {
        context.userPreferenceDataStore.edit {
            it[DataStoreUtil.USERNAME_PREF_KEY] = name
        }
    }

    fun readFromDataStore(): Flow<Boolean> = context.userPreferenceDataStore.data
        .map {
            it[DataStoreUtil.USERNAME_PREF_KEY] != null
        }
}