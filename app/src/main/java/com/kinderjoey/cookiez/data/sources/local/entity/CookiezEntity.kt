package com.kinderjoey.cookiez.data.sources.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cookiez_table")
data class CookiezEntity(

    @PrimaryKey
    @ColumnInfo(name = "key")
    val key: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "time")
    val time: Int,
    @ColumnInfo(name = "difficulty")
    val difficulty: String,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "type")
    val type: String,
)
