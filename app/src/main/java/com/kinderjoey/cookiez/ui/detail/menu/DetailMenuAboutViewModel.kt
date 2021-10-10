package com.kinderjoey.cookiez.ui.detail.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kinderjoey.cookiez.data.repository.ICookiezRepository
import kotlinx.coroutines.Dispatchers

class DetailMenuAboutViewModel(private val cookiezRepository: ICookiezRepository): ViewModel() {

    fun getDetailMenu(menuName: String) = cookiezRepository
        .getDetailMenu(menuName)
        .asLiveData(Dispatchers.IO)
}