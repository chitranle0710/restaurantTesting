package com.example.restauranttestingapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.restauranttestingapplication.model.DaysConverter
import com.example.restauranttestingapplication.model.Restaurants

@Database(entities = [Restaurants::class], version = 2, exportSchema = false)
@TypeConverters(DaysConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "restaurant_database"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }

    }
}