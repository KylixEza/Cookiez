package com.kinderjoey.cookiez.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kinderjoey.cookiez.data.repository.ICookiezRepository
import kotlinx.coroutines.Dispatchers

class HomeViewModel(private val iCookiezRepository: ICookiezRepository): ViewModel() {

    fun getPopularMenus() = iCookiezRepository.getPopularMenus().asLiveData(Dispatchers.IO)
    fun getExclusiveMenus() = iCookiezRepository.getExclusiveMenus().asLiveData(Dispatchers.IO)
}