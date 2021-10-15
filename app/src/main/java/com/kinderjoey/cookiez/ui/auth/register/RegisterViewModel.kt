package com.kinderjoey.cookiez.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kinderjoey.cookiez.data.repository.IAuthRepository
import com.kinderjoey.cookiez.model.User

class RegisterViewModel(private val repository: IAuthRepository) : ViewModel() {
    fun signUp(email:String, password: String, user: User) =
        repository.signUpUser(email,password, user).asLiveData()
}