package com.temp.view.customview

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.temp.R
import com.temp.utils.ValidationUtils

class EmailValidator(private val T : TextView) :  TextWatcher {

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        val data = when {
            "" == s.toString() -> null
            ValidationUtils.isEmailValid(s.toString()) -> T.context.getDrawable(R.drawable.ic_check_mark)
            else -> T.context.getDrawable(R.drawable.ic_cancel)
        }
        T.setCompoundDrawablesWithIntrinsicBounds(null, null, data, null)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
}