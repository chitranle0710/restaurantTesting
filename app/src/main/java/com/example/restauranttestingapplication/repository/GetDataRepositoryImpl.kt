package com.example.restauranttestingapplication.repository

import com.example.restauranttestingapplication.data.remote.API
import com.example.restauranttestingapplication.data.remote.BaseAPIService
import com.example.restauranttestingapplication.mapper.RestaurantMapper
import com.example.restauranttestingapplication.model.RestaurantsTime
import com.example.restauranttestingapplication.utils.ResultWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDataRepositoryImpl @Inject constructor(private val api: API) : GetDataRemoteRepository,
    BaseAPIService() {
    override suspend fun getDataList(): ResultWrapper<RestaurantsTime> = apiCall(
        call = {
            api.getRestaurant()
        },
        mapper = { data -> RestaurantMapper.mapper(data) }
    )
}