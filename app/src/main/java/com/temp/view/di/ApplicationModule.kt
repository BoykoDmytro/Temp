package com.temp.view.di

import android.app.Application
import dagger.Module
import dagger.Provides
import com.temp.domain.interactor.SoapUseCase
import com.temp.utils.PreferenceHelper
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    fun provideApp() = application

    @Provides
    @Singleton
    internal fun getPreferenceHelper(): PreferenceHelper {
        return PreferenceHelper(application)
    }

    @Provides
    @Singleton
    internal fun getSoapUseCase(): SoapUseCase {
        return SoapUseCase()
    }
}