package com.kinderjoey.cookiez.data.sources.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cookiez_table")
data class CookiezEntity(

    @PrimaryKey
    val key: String
)
