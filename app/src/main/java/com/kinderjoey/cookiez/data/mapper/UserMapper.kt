package com.kinderjoey.cookiez.data.mapper

import com.kinderjoey.cookiez.data.sources.firestore.response.UserResponse
import com.kinderjoey.cookiez.data.sources.local.entity.UserEntity
import com.kinderjoey.cookiez.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun UserResponse.toEntity(): UserEntity =
    UserEntity(uid, name, phoneNumber, email, address, xp, coin, cookiezWallet, transaction,avatar)

fun UserEntity.toModel(): User =
    User(id = uid,name, phoneNumber, email, address, xp, coin, cookiezWallet, transaction,avatar)

fun Flow<UserEntity>.toFlowModel(): Flow<User> =
    this.map {
        it.toModel()
    }