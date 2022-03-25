package com.example.restauranttestingapplication.utils

import android.view.View

fun View?.beVisible() {
    this?.visibility = View.VISIBLE
}

fun View?.beInVisible() {
    this?.visibility = View.INVISIBLE
}

fun View?.beGone() {
    this?.visibility = View.GONE
}