package com.kinderjoey.cookiez.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kinderjoey.cookiez.data.sources.datastore.DataStorePlayground
import kotlinx.coroutines.Dispatchers

class SplashViewModel(private val application: Application): ViewModel() {

    private val dataStorePlayground = DataStorePlayground.getInstance(application)

    fun readPrefEmail() = dataStorePlayground.readPrefEmail().asLiveData(Dispatchers.IO)
    fun readPrefHaveRunAppBefore() = dataStorePlayground.readPrefHaveRunAppBefore().asLiveData(Dispatchers.IO)
}