package com.kinderjoey.demo.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kinderjoey.demo.data.datastore.DataStorePlayground
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(application: Application): AndroidViewModel(application) {

    private val dataStorePlayground = DataStorePlayground.getInstance(application)

    fun saveToDataStore(name: String) = viewModelScope.launch {
        dataStorePlayground.saveToDataStore(name)
    }

    fun readFromDataStore() = dataStorePlayground.readFromDataStore().asLiveData(Dispatchers.IO)
}