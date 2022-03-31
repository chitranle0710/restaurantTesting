package com.example.restauranttestingapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RestaurantsTime(
    @Expose
    @SerializedName("timestamp")
    val timestamp: Long,

    @Expose
    @SerializedName("restaurants")
    val restaurants: List<RestaurantData>
)

data class RestaurantData(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("operatingHours")
    val operatingHours: String
)