package com.c241ps294.sikarir.data.helper

import androidx.room.TypeConverter
import com.c241ps294.sikarir.data.remote.response.ListMajorItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromListMajorItemList(value: List<ListMajorItem?>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<ListMajorItem?>?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toListMajorItemList(value: String): List<ListMajorItem?>? {
        val gson = Gson()
        val type = object : TypeToken<List<ListMajorItem?>?>() {}.type
        return gson.fromJson(value, type)
    }
}