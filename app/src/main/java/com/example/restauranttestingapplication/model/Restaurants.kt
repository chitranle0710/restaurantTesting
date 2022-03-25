package com.example.restauranttestingapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity(tableName = "restaurant_table")
data class Restaurants(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val operation: Boolean,
    @TypeConverters(DaysConverter::class)
    val schedule: List<Days> = listOf()
)

data class Days(
    val idDay: Int,
    val nameDay: String,
    val timeOpen: Long,
    val timeClose: Long
)
