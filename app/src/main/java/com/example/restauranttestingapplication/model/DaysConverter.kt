package com.example.restauranttestingapplication.model

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken

import com.google.gson.Gson
import java.util.*

class DaysConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToList(data: String?): List<Days> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Days>>() {
        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Days>): String {
        return gson.toJson(someObjects)
    }
}