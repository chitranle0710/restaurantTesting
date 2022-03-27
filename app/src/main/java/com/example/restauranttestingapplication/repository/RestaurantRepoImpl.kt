package com.example.restauranttestingapplication.repository

import com.example.restauranttestingapplication.data.local.RestaurantDao
import com.example.restauranttestingapplication.model.Restaurants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepoImpl @Inject constructor(private val restaurantDao: RestaurantDao) :
    RestaurantRepo {
    override fun insertRestaurant(restaurants: Restaurants) = restaurantDao.insert(restaurants)

    override fun getDataRestaurants(): Flow<List<Restaurants>> = flow {
        emit(restaurantDao.getAllRestaurants())
    }

    override fun clearData() = restaurantDao.deleteAll()
}