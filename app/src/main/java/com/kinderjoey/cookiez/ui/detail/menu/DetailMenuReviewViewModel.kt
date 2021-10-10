package com.kinderjoey.cookiez.ui.detail.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kinderjoey.cookiez.data.repository.ICookiezRepository
import kotlinx.coroutines.Dispatchers

class DetailMenuReviewViewModel(private val cookiezRepository: ICookiezRepository): ViewModel() {

    fun getReviews(menuName: String) = cookiezRepository
        .getReviews(menuName)
        .asLiveData(Dispatchers.IO)
}