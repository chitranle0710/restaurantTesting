package com.example.restauranttestingapplication.utils

import android.content.Context
import android.content.SharedPreferences

object SharePrefs {
    private var shareReference: SharedPreferences? = null

    fun instance(context: Context) {
        if (shareReference == null)
            shareReference = context.getSharedPreferences(Constant.APP, Context.MODE_PRIVATE)
    }

    fun savePutData(context: Context, putData: Boolean) {
        instance(context)
        shareReference?.edit()?.putBoolean(Constant.ADD_DATA, putData)?.apply()
    }

    fun getPutData(context: Context): Boolean? {
        instance(context)
        return shareReference?.getBoolean(Constant.ADD_DATA, false)
    }
}