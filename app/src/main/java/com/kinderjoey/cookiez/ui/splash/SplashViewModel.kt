package com.kinderjoey.cookiez.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.kinderjoey.cookiez.data.datastore.DataStorePlayground
import kotlinx.coroutines.Dispatchers

class SplashViewModel(application: Application): AndroidViewModel(application) {

    private val dataStorePlayground = DataStorePlayground.getInstance(application)

    fun readFromDataStore() = dataStorePlayground.readFromDataStore().asLiveData(Dispatchers.IO)
}