package com.c241ps294.sikarir.data.helper

import androidx.room.TypeConverter
import com.c241ps294.sikarir.data.remote.response.ListJurusanTerkaitItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromListJurusanTerkaitItemList(value: List<ListJurusanTerkaitItem?>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<ListJurusanTerkaitItem?>?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toListJurusanTerkaitItemList(value: String): List<ListJurusanTerkaitItem?>? {
        val gson = Gson()
        val type = object : TypeToken<List<ListJurusanTerkaitItem?>?>() {}.type
        return gson.fromJson(value, type)
    }
}