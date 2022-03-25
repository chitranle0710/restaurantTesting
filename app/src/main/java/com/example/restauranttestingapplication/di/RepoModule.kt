package com.example.restauranttestingapplication.di

import com.example.restauranttestingapplication.repository.RestaurantRepo
import com.example.restauranttestingapplication.repository.RestaurantRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepoModule {

    @Binds
    fun provideRestaurantRepository(restaurantRepoImpl: RestaurantRepoImpl): RestaurantRepo

}