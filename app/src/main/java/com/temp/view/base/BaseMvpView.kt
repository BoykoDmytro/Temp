package com.temp.view.base

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType



interface BaseMvpView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgressDialog()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideProgressDialog()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideKeyBoard()
}