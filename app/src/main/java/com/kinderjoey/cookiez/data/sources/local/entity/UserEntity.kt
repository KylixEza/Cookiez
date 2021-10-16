package com.kinderjoey.cookiez.data.sources.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val uid:String ="",
    val name: String ="",
    val phoneNumber: String = "",
    val email: String ="",
    val address: String ="",
    val xp: Int = 0,
    val coin: Int = 0,
    val cookiezWallet: Int = 0,
    val transaction: List<String>? = listOf(),
    val avatar: String = ""
)
class ListConverter {
    @TypeConverter
    fun toListString(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(torrent: List<String>): String {
        val type = object: TypeToken<List<String>>() {}.type
        return Gson().toJson(torrent, type)
    }
}