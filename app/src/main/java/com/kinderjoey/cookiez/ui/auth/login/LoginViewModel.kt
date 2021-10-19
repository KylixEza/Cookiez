package com.kinderjoey.cookiez.ui.auth.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kinderjoey.cookiez.data.repository.IAuthRepository
import com.kinderjoey.cookiez.data.sources.datastore.DataStorePlayground
import kotlinx.coroutines.launch

class LoginViewModel( private val repository: IAuthRepository, private val application: Application): ViewModel() {

    private val datastore = DataStorePlayground.getInstance(application)

    fun savePrefEmail(email: String) = viewModelScope.launch {
        datastore.savePrefEmail(email)
    }

    fun savePrefHaveRunAppBefore(isFirstTime: Boolean) = viewModelScope.launch {
        datastore.savePrefHaveRunAppBefore(isFirstTime)
    }

    fun signIn(email:String, password: String) =
        repository.signInUser(email,password).asLiveData()

}