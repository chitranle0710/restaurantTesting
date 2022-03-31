package com.example.restauranttestingapplication.utils

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

object AppUtils {
    private val gson = Gson()

    fun <T> deepCopy(item: T?, clazz: Class<T>): T {
        val str = gson.toJson(item)
        return gson.fromJson(str, clazz)
    }

    @SuppressLint("SimpleDateFormat")
    fun convertTime(startTimeInMillis: Long): String {
        return (SimpleDateFormat("mm:ss:SSS")).format(Date(startTimeInMillis));
    }

    fun getCurrentHour(): Int {
        val rightNow = Calendar.getInstance()
        return rightNow[Calendar.HOUR_OF_DAY]
    }

    inline fun getValueAnimator(
        forward: Boolean = true,
        duration: Long,
        interpolator: TimeInterpolator,
        crossinline updateListener: (progress: Float) -> Unit
    ): ValueAnimator {
        val valueAnimator =
            if (forward) ValueAnimator.ofFloat(0f, 1f)
            else ValueAnimator.ofFloat(1f, 0f)
        valueAnimator.addUpdateListener { updateListener(it.animatedValue as Float) }
        valueAnimator.duration = duration
        valueAnimator.interpolator = interpolator
        return valueAnimator
    }
}