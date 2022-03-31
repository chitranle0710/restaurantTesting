package com.example.restauranttestingapplication.repository

import com.example.restauranttestingapplication.model.RestaurantsTime
import com.example.restauranttestingapplication.utils.ResultWrapper

interface GetDataRemoteRepository {
    suspend fun getDataList(): ResultWrapper<RestaurantsTime>
}