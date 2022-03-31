package com.example.restauranttestingapplication.mapper

import com.example.restauranttestingapplication.model.RestaurantsTime

object RestaurantMapper {
    fun mapper(restaurantsTime: RestaurantsTime): RestaurantsTime {
        return RestaurantsTime(
            restaurantsTime.timestamp,
            restaurantsTime.restaurants
        )
    }
}