package com.example.restauranttestingapplication.utils

import java.lang.Exception

sealed class ResultWrapper<T> {
    data class Success<T>(val data: T) : ResultWrapper<T>()
    data class Error<T>(val exception: Exception) : ResultWrapper<T>()
}