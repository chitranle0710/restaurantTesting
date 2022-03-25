package com.example.restauranttestingapplication

import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout

class DialogLoading(context: Context) : Dialog(context, R.style.Theme_Dialog) {

    init {
        initDialog()
    }

    private fun initDialog() {
        setContentView(R.layout.progress_bar)
        setCanceledOnTouchOutside(false)
        window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        window?.setGravity(Gravity.BOTTOM)
        window?.setWindowAnimations(R.style.BottomDialogAlpha)
    }

    fun showDialog() {
        if (isShowing) return
        show()
        Handler(Looper.getMainLooper()).postDelayed({ dismiss() }, 10000)
    }

    fun closeDialog() {
        dismiss()
    }

    override fun onBackPressed() {

    }
}