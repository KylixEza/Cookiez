package com.kinderjoey.cookiez.data.sources.local

import com.kinderjoey.cookiez.data.sources.local.entity.UserEntity
import com.kinderjoey.cookiez.data.sources.local.room.UserDao

class LocalDataSource(private val userDao: UserDao,) {
    suspend fun insertUser(userEntity: UserEntity): Unit =
        userDao.insertUser(userEntity)
}