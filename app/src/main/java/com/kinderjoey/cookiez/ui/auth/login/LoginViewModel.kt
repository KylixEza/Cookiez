package com.kinderjoey.cookiez.ui.auth.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kinderjoey.cookiez.data.repository.IAuthRepository
import com.kinderjoey.cookiez.data.sources.datastore.DataStorePlayground
import com.kinderjoey.cookiez.model.User
import kotlinx.coroutines.launch

class LoginViewModel( private val repository: IAuthRepository): ViewModel() {

/*    private val dataStorePlayground = DataStorePlayground.getInstance(application)

    fun saveToDataStore(name: String) = viewModelScope.launch {
        dataStorePlayground.savePrefUsername(name)
    }*/

    fun signIn(email:String, password: String) =
        repository.signInUser(email,password).asLiveData()

}