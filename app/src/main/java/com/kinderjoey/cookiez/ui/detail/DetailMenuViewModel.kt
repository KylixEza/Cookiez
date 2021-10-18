package com.kinderjoey.cookiez.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kinderjoey.cookiez.data.repository.ICookiezRepository
import com.kinderjoey.cookiez.model.Favorite
import kotlinx.coroutines.Dispatchers

class DetailMenuViewModel(private val cookiezRepository: ICookiezRepository): ViewModel() {

    fun getDetailMenu(menuName: String) = cookiezRepository
        .getDetailMenu(menuName)
        .asLiveData(Dispatchers.IO)

    fun isFavorite(uid: String, menuName: String) = cookiezRepository
        .isFavorite(uid, menuName)
        .asLiveData(Dispatchers.IO)

    fun addToFavorite(uid: String, menuName: String, data: Favorite) = cookiezRepository
        .addToFavorite(uid, menuName, data)
        .asLiveData(Dispatchers.IO)

    fun removeFromFavorite(uid: String, menuName: String) = cookiezRepository
        .removeFromFavorite(uid, menuName)
        .asLiveData(Dispatchers.IO)
}