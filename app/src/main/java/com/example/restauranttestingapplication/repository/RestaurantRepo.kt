package com.example.restauranttestingapplication.repository

import com.example.restauranttestingapplication.model.Restaurants
import kotlinx.coroutines.flow.Flow

interface RestaurantRepo {
    fun insertRestaurant(restaurants: Restaurants)
    fun getDataRestaurants(): Flow<List<Restaurants>>
    fun clearData()
}