package com.temp.view.di

import dagger.Component
import com.temp.utils.PreferenceHelper
import com.temp.view.presenter.LoginPresenter
import com.temp.view.presenter.SplashPresenter
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun getPreferenceHelper(): PreferenceHelper

    fun inject(presenter: SplashPresenter)
    fun inject(presenter: LoginPresenter)
}