package com.kinderjoey.cookiez.data.sources.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreUtil {

    const val DATA_STORE_NAME = "COOKIEZ_DATA_STORE"
    val EMAIL_PREF_KEY = stringPreferencesKey("email")
    val HAVE_RUN_APP_BEFORE = booleanPreferencesKey("isFirstTime")
}