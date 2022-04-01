package com.example.restauranttestingapplication.utils

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.function.Consumer

object AppUtils {
    private val gson = Gson()

    fun <T> deepCopy(item: T?, clazz: Class<T>): T {
        val str = gson.toJson(item)
        return gson.fromJson(str, clazz)
    }

    fun convertTimeToDayOfWeek(s: Long): String {
        val simpleDateFormat = SimpleDateFormat("EEE", Locale.ENGLISH)
        return simpleDateFormat.format(s * 1000L)
    }

    fun convertTimeToHourMinutes(s: Long): String {
        val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)
        return simpleDateFormat.format(s * 1000L)
    }


    @Throws(ParseException::class)
    fun parseDate(text: String): Date? {
        val dateFormat = SimpleDateFormat(
            "HH:mm",
            Locale.US
        )
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        return dateFormat.parse(text)
    }

    fun isBetweenValidTime(startTime: Date?, endTime: Date, validateTime: Date): Boolean {
        var validTimeFlag = false
        if (endTime <= startTime) {
            if (validateTime < endTime || validateTime >= startTime) {
                validTimeFlag = true
            }
        } else if (validateTime < endTime && validateTime >= startTime) {
            validTimeFlag = true
        }
        return validTimeFlag
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    fun convertDaysOfWeek(operatingHours: String): List<OperatingHoursInformation> {
        val operatingHoursInformation: MutableList<OperatingHoursInformation> = ArrayList()
        val groupByTime = operatingHours.split("/".toRegex()).toTypedArray()
        for (groupDateTime in groupByTime) {
            val groupDayOfWeek = groupDateTime.split("[0-9]".toRegex()).toTypedArray()[0]
            val days = groupDayOfWeek.split(",".toRegex()).toTypedArray()
            val groupTime = groupDateTime.substring(groupDayOfWeek.length)
            val groupTimes = groupTime.split("-".toRegex()).toTypedArray()
            val startTime = groupTimes[0].trim { it <= ' ' }
            val endTime = groupTimes[1].trim { it <= ' ' }
            for (day in days) {
                getDays(day).forEach(Consumer { x: String? ->
                    operatingHoursInformation.add(
                        OperatingHoursInformation(
                            x!!, startTime, endTime
                        )
                    )
                })
            }
        }
        return operatingHoursInformation
    }

    private fun getDays(day: String): List<String?> {
        val days: MutableList<String?> = ArrayList()
        val dayOfWeekConstant = "Mon-Tue-Wed-Thu-Fri-Sat-Sun"
        if (day.contains("-")) {
            val date = day.split("-".toRegex()).toTypedArray()
            date[0] = date[0].trim { it <= ' ' }
            date[1] = date[1].trim { it <= ' ' }
            val rangeDate = dayOfWeekConstant.substring(
                dayOfWeekConstant.indexOf(date[0]), dayOfWeekConstant.indexOf(
                    date[1]
                ) + date[1].length
            )
            days.addAll(listOf(*rangeDate.split("-".toRegex()).toTypedArray()))
        } else {
            days.add(day.trim { it <= ' ' })
        }
        return days
    }
}
