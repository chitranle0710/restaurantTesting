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
    val restaurantMutableLiveData = MutableLiveData<List<Restaurants>>()

    fun insertData(restaurants: Restaurants) {
        CoroutineScope(Dispatchers.IO).launch {
            repo.insertRestaurant(restaurants)
        }
    }

    fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            repo.getDataRestaurants().collect { data ->
                restaurantMutableLiveData.postValue(data)
            }
        }
    }
}