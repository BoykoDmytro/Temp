package com.temp.utils

import android.content.Context
import android.content.SharedPreferences
import com.temp.view.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PreferenceHelper @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        const val PREF_NAME = "hp_prefs"
        const val CREDENTIALS = "credentials"
    }

    private var mSharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun clearAllPreferences() {
        mSharedPreferences.edit().clear().apply()
    }

    fun setCredentials(credentials: String) {
        mSharedPreferences.edit().putString(CREDENTIALS, credentials).apply()
    }

    fun geredentials(): String? {
        return mSharedPreferences.getString(CREDENTIALS, null)
    }
}
