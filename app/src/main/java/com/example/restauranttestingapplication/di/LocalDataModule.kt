package com.example.restauranttestingapplication.di

import android.app.Application
import androidx.room.Room
import com.example.restauranttestingapplication.data.local.AppDatabase
import com.example.restauranttestingapplication.data.local.RestaurantDao
import com.example.restauranttestingapplication.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, Constant.database)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun resDao(db: AppDatabase): RestaurantDao {
        return db.restaurantDao()
    }
}