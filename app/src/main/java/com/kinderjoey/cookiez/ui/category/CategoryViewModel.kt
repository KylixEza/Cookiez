package com.kinderjoey.cookiez.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kinderjoey.cookiez.data.repository.ICookiezRepository
import kotlinx.coroutines.Dispatchers

class CategoryViewModel(private val cookiezRepository: ICookiezRepository): ViewModel() {

    fun getCategoryMenus(category: String) = cookiezRepository.getCategoryMenus(category).asLiveData(Dispatchers.IO)
    fun getAllMenus() = cookiezRepository.getAllMenus().asLiveData(Dispatchers.IO)
}