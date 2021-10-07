package com.kinderjoey.cookiez.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kinderjoey.cookiez.data.repository.ICookiezRepository
import kotlinx.coroutines.Dispatchers

class HomeViewModel(private val cookiezRepository: ICookiezRepository): ViewModel() {

    fun getPopularMenus() = cookiezRepository.getPopularMenus().asLiveData(Dispatchers.IO)
    fun getExclusiveMenus() = cookiezRepository.getExclusiveMenus().asLiveData(Dispatchers.IO)
}