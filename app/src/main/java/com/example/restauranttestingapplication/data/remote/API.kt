package com.example.restauranttestingapplication.data.remote

import com.example.restauranttestingapplication.model.RestaurantsTime
import retrofit2.http.GET


interface API {
    // TODO 4 methods are declared here
    @GET("homework")
    suspend fun getRestaurant(): RestaurantsTime
}