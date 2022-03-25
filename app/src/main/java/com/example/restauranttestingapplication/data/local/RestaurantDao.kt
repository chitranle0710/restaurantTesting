package com.example.restauranttestingapplication.data.local

import androidx.room.*
import com.example.restauranttestingapplication.model.Restaurants

@Dao
interface RestaurantDao {

    @Insert
    fun insert(restaurants: Restaurants)

    @Update
    fun update(restaurants: Restaurants)

    @Delete
    fun delete(restaurants: Restaurants)

    @Query("delete from restaurant_table")
    fun deleteAll()

    @Query("SELECT * FROM restaurant_table")
    fun getAllRestaurants(): List<Restaurants>
}