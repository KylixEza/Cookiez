package com.kinderjoey.cookiez.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kinderjoey.cookiez.data.repository.ICookiezRepository
import kotlinx.coroutines.Dispatchers

class FavoriteViewModel(private val cookiezRepository: ICookiezRepository) : ViewModel() {

    fun getAllFavorites(uid: String) = cookiezRepository
        .getAllFavorites(uid)
        .asLiveData(Dispatchers.IO)
}