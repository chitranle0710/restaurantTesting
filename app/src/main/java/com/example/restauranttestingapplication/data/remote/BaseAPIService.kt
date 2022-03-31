package com.example.restauranttestingapplication.data.remote

import android.util.Log
import com.example.restauranttestingapplication.utils.ResultWrapper

abstract class BaseAPIService {
    protected suspend fun <T, R> apiCall(
        call: suspend () -> R,
        mapper: ((R) -> T)
    ): ResultWrapper<T> {
        return try {
            val r = call()
            if (r is retrofit2.Response<*>) {
                if (!r.isSuccessful) {
                    val body = r.errorBody()!!
                    Log.d("BaseAPIService", "$body")
                    ResultWrapper.Error(java.lang.Exception())
                } else ResultWrapper.Success(mapper(r))
            } else ResultWrapper.Success(mapper(r))
        } catch (exception: Throwable) {
            exception.printStackTrace()
            Log.d("BaseAPIService", "$exception")
            return ResultWrapper.Error(java.lang.Exception())
        }
    }
}
