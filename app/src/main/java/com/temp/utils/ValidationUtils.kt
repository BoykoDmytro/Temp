package com.temp.utils

import android.support.annotation.Nullable
import android.util.Patterns

class ValidationUtils{

    companion object {
        fun isEmailValid(@Nullable email: String?) : Boolean{
            return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

}