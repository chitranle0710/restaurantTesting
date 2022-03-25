package com.example.restauranttestingapplication.base

import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun progressBar(isShow: Boolean) {
        if (isShow) (context as BaseActivity).progressBarView() else (context as BaseActivity).dismissProgressBar()
    }

}