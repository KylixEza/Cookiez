package com.kinderjoey.cookiez.ui.auth.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kinderjoey.cookiez.data.repository.IAuthRepository


class LoginViewModel( private val repository: IAuthRepository): ViewModel() {

/*    private val dataStorePlayground = DataStorePlayground.getInstance(application)

    fun saveToDataStore(name: String) = viewModelScope.launch {
        dataStorePlayground.savePrefUsername(name)
    }*/

    fun signIn(email:String, password: String) =
        repository.signInUser(email,password).asLiveData()

}