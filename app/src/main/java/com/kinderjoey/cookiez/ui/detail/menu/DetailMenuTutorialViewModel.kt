package com.kinderjoey.cookiez.ui.detail.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kinderjoey.cookiez.data.repository.ICookiezRepository
import kotlinx.coroutines.Dispatchers

class DetailMenuTutorialViewModel(private val cookiezRepository: ICookiezRepository): ViewModel() {

    fun getIngredients(menuName: String) = cookiezRepository
        .getIngredients(menuName)
        .asLiveData(Dispatchers.IO)

    fun getSteps(menuName: String) = cookiezRepository
        .getSteps(menuName)
        .asLiveData(Dispatchers.IO)

}