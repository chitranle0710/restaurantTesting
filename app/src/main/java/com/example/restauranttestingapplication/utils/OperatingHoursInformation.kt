package com.example.restauranttestingapplication.utils

data class OperatingHoursInformation(
    val dayOfWeek: String,
    val startTime: String,
    val endTime: String
) {
    override fun toString(): String {
        return "operatingHoursInformation{" +
                "dayOfWeek='" + dayOfWeek + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}'
    }
}