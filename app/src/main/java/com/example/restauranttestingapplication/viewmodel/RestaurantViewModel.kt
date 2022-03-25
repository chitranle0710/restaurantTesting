package com.example.restauranttestingapplication.viewmodel

import androidx.lifecycle.*
import com.example.restauranttestingapplication.model.Restaurants
import com.example.restauranttestingapplication.repository.RestaurantRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(private val repo: RestaurantRepo) : ViewModel() {
    val restaurantMutableLiveData = MutableLiveData<MutableList<Restaurants>>()
    val loadingData = MutableLiveData<Boolean>()
    fun insertData(restaurants: Restaurants) {
        loadingData.postValue(false)
        CoroutineScope(Dispatchers.IO).launch {
            repo.insertRestaurant(restaurants)
            getData()
        }

    }

    fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            loadingData.postValue(false)
            repo.getDataRestaurants().collect { data ->
                restaurantMutableLiveData.postValue(data)
                loadingData.postValue(true)
            }
        }
    }

    fun clearData() {
        CoroutineScope(Dispatchers.IO).launch {
            loadingData.postValue(false)
            repo.clearData()
            getData()
            loadingData.postValue(true)
        }
    }
}