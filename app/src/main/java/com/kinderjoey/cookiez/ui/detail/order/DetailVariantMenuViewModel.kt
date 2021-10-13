package com.kinderjoey.cookiez.ui.detail.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kinderjoey.cookiez.data.repository.ICookiezRepository
import kotlinx.coroutines.Dispatchers

class DetailVariantMenuViewModel(private val cookiezRepository: ICookiezRepository): ViewModel() {

    fun getVariantMenu(menuName: String) = cookiezRepository
        .getVariantMenu(menuName)
        .asLiveData(Dispatchers.IO)
}