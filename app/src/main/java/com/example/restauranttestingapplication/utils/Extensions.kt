package com.example.restauranttestingapplication.utils

import android.view.View
import java.util.ArrayList

fun View?.beVisible() {
    this?.visibility = View.VISIBLE
}

fun View?.beInVisible() {
    this?.visibility = View.INVISIBLE
}

fun View?.beGone() {
    this?.visibility = View.GONE
}

fun <T> List<T>.toArrayList(): ArrayList<T> {
    return ArrayList(this)
}