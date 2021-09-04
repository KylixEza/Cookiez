package com.kinderjoey.demo.ui.auth.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinderjoey.demo.data.datastore.DataStorePlayground
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {

    private val dataStorePlayground = DataStorePlayground.getInstance(application)

    fun saveToDataStore(name: String) = viewModelScope.launch {
        dataStorePlayground.saveToDataStore(name)
    }

}