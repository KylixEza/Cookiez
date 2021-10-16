package com.kinderjoey.cookiez.data.sources.local

import com.kinderjoey.cookiez.data.sources.local.entity.UserEntity
import com.kinderjoey.cookiez.data.sources.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val userDao: UserDao,) {
    suspend fun insertUser(userEntity: UserEntity): Unit =
        userDao.insertUser(userEntity)

    fun selectUserByUid(uid: String): Flow<UserEntity> =
        userDao.selectUserByUid(uid)
}