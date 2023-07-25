package com.example.gameofthrones.data.entities

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Type
class StringListTypeConverter  {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(json: String?): List<String>? {
        val type: Type = object : TypeToken<List<String>?>() {}.type
        return gson.fromJson(json, type)
    }
}