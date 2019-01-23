package com.temp.view

import android.app.Application
import com.temp.BuildConfig
import com.temp.view.di.AppComponent
import com.temp.view.di.AppModule
import com.temp.view.di.DaggerAppComponent
import timber.log.Timber.DebugTree
import timber.log.Timber


class PHApplication : Application() {

    companion object {
        lateinit var component: AppComponent
        fun getAppComponent(): AppComponent {
            return component
        }
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}
