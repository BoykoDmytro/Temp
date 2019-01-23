package com.temp.view.presenter

import android.text.TextUtils
import com.arellomobile.mvp.InjectViewState
import com.temp.utils.PreferenceHelper
import com.temp.view.PHApplication
import com.temp.view.base.BasePresenter
import com.temp.view.view.SplashView
import javax.inject.Inject

@InjectViewState
class SplashPresenter : BasePresenter<SplashView>() {

    @Inject
    lateinit var prefHelper: PreferenceHelper

    init {
        PHApplication.getAppComponent().inject(this)
    }

    fun navigateTo() {
        if (TextUtils.isEmpty(prefHelper.geredentials())) {
            viewState.navigateToLoginScreen()
        } else
            viewState.navigateToMainScreen()
    }
}