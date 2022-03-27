package com.example.restauranttestingapplication.utils

import com.example.restauranttestingapplication.model.Restaurants
import com.google.gson.Gson

object AppUtils {
    private val gson = Gson()

    fun <T> deepCopy(item: T?, clazz: Class<T>): T {
        val str = gson.toJson(item)
        return gson.fromJson(str, clazz)
    }

    val listRestaurant = mutableListOf<Restaurants>()
}