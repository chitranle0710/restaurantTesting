package com.example.restauranttestingapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class ErrorResponse {
    @Expose
    @SerializedName("error")
    var error: String? = null
}