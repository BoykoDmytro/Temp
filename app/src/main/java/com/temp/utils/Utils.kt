package com.temp.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class Utils{

    companion object {

        fun hideKeyboard(activity: Activity) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            val v = activity.currentFocus
            if (imm == null) return
            if (v != null) {
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                v.clearFocus()
            } else {
                imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
            }
        }

        fun hideKeyboard(view: View) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}