package com.kinderjoey.cookiez.data.sources.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kinderjoey.cookiez.data.sources.local.entity.CookiezEntity

@Database(entities = [CookiezEntity::class], version = 1, exportSchema = false)
abstract class CookiezDatabase: RoomDatabase() {
    abstract fun cookiezDao(): CookiezDao
}