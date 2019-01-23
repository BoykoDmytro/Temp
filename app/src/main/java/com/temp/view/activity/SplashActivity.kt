package com.temp.view.activity

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.temp.R
import com.temp.view.base.BaseActivity
import com.temp.view.presenter.SplashPresenter
import com.temp.view.view.SplashView

class SplashActivity : BaseActivity(), SplashView {

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        presenter.navigateTo()
    }

    override fun navigateToMainScreen() {
        startActivity(MainActivity.getInstance(this))
    }

    override fun navigateToLoginScreen() {
        startActivity(LoginActivity.getInstance(this))
    }
}