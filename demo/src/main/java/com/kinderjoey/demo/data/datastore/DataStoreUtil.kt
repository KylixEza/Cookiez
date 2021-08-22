package com.kinderjoey.demo.data.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreUtil {

    const val DATA_STORE_NAME = "COOKIEZ_DATA_STORE"
    val USERNAME_PREF_KEY = stringPreferencesKey("username")
}