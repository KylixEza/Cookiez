package com.kinderjoey.cookiez.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.kinderjoey.cookiez.data.sources.datastore.DataStorePlayground
import kotlinx.coroutines.Dispatchers

class SplashViewModel(application: Application): AndroidViewModel(application) {

    private val dataStorePlayground = DataStorePlayground.getInstance(application)

    fun readPrefUsername() = dataStorePlayground.readPrefUsername().asLiveData(Dispatchers.IO)
    fun readPrefHaveRunAppBefore() = dataStorePlayground.readPrefHaveRunAppBefore().asLiveData(Dispatchers.IO)
}