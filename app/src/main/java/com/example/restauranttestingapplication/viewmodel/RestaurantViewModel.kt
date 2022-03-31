package com.example.restauranttestingapplication.viewmodel

import androidx.lifecycle.*
import com.example.restauranttestingapplication.model.Restaurants
import com.example.restauranttestingapplication.model.RestaurantsTime
import com.example.restauranttestingapplication.repository.GetDataRemoteRepository
import com.example.restauranttestingapplication.repository.RestaurantRepo
import com.example.restauranttestingapplication.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repo: RestaurantRepo,
    private val remoteRepository: GetDataRemoteRepository
) : ViewModel() {
    val loadingData = MutableLiveData<Boolean>()
    val restaurantTimeMutableLiveData = MutableLiveData<RestaurantsTime>()

    fun getDataRemote() {
        viewModelScope.launch {
            loadingData.postValue(false)
            when (val value = remoteRepository.getDataList()) {
                is ResultWrapper.Success -> {
                    restaurantTimeMutableLiveData.postValue(value.data)
                }
                else -> {
                }
            }
            loadingData.postValue(true)
        }
    }

}