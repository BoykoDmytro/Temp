package com.temp.view.view

import com.temp.view.base.BaseMvpView

interface LoginView : BaseMvpView{

    fun navigateToMainScreen()

    fun showError(exception: Exception)
}