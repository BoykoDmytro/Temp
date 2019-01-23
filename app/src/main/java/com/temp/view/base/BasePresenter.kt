package com.temp.view.base

import com.arellomobile.mvp.MvpPresenter

abstract class BasePresenter<T : BaseMvpView> : MvpPresenter<T>()