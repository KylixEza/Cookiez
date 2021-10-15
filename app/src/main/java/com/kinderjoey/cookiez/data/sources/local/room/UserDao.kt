package com.kinderjoey.cookiez.data.sources.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.kinderjoey.cookiez.data.sources.local.entity.UserEntity
import com.kinderjoey.cookiez.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

}