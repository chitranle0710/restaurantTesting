package com.example.restauranttestingapplication

import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DateTest {
    private val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.ENGLISH)

    @Test
    fun testDate() {
        val time = 1560507488
        println(getDateString(time))
    }

    private fun getDateString(time: Long): String = simpleDateFormat.format(time * 1000L)

    private fun getDateString(time: Int): String = simpleDateFormat.format(time * 1000L)
}