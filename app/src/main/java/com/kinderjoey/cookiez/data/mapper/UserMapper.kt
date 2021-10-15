package com.kinderjoey.cookiez.data.mapper

import com.kinderjoey.cookiez.data.sources.firestore.response.UserResponse
import com.kinderjoey.cookiez.data.sources.local.entity.UserEntity

fun UserResponse.toEntity(): UserEntity =
    UserEntity(uid, name, phoneNumber, email, address, xp, coin, cookiezWallet, transaction)