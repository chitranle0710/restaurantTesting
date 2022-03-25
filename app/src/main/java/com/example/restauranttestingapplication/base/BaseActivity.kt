package com.example.restauranttestingapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.restauranttestingapplication.DialogLoading

abstract class BaseActivity : AppCompatActivity() {
    private val dialogLoading by lazy {
        DialogLoading(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun progressBarView() {
        if (dialogLoading.isShowing) return
        dialogLoading.showDialog()
    }

    fun dismissProgressBar() {
        if (dialogLoading.isShowing) dialogLoading.closeDialog()
    }

    fun progressBar(isShow: Boolean) {
        if (isShow) progressBarView() else dismissProgressBar()
    }
}