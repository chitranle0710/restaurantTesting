package com.example.restauranttestingapplication.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.restauranttestingapplication.DialogLoading
import com.example.restauranttestingapplication.MainActivity

abstract class BaseFragment : Fragment() {
    private val dialogLoading by lazy {
        DialogLoading(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun progressBarView() {
        if (dialogLoading.isShowing) return
        dialogLoading.showDialog()
    }

    private fun dismissProgressBar() {
        if (dialogLoading.isShowing) dialogLoading.closeDialog()
    }

    fun progressBar(isShow: Boolean) {
        if (!isShow) progressBarView() else dismissProgressBar()
    }

}