package com.kinderjoey.cookiez.data.sources.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kinderjoey.cookiez.data.sources.local.entity.CookiezEntity
import com.kinderjoey.cookiez.data.sources.local.entity.ListConverter
import com.kinderjoey.cookiez.data.sources.local.entity.UserEntity

@Database(entities = [CookiezEntity::class,UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class CookiezDatabase: RoomDatabase() {
    abstract fun cookiezDao(): CookiezDao
    abstract fun userDao() : UserDao
}