package com.kinderjoey.cookiez.ui.auth.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kinderjoey.cookiez.data.repository.IAuthRepository
import com.kinderjoey.cookiez.data.sources.datastore.DataStorePlayground
import com.kinderjoey.cookiez.model.User
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: IAuthRepository,
                        private val application: Application) : ViewModel() {

    private val datastore = DataStorePlayground.getInstance(application)

    fun savePrefEmail(email: String) = viewModelScope.launch {
        datastore.savePrefEmail(email)
    }

    fun savePrefHaveRunAppBefore(isFirstTime: Boolean) = viewModelScope.launch {
        datastore.savePrefHaveRunAppBefore(isFirstTime)
    }

    fun signUp(email:String, password: String, user: User) =
        repository.signUpUser(email,password, user).asLiveData()
}