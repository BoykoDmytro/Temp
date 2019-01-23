package com.temp.view.base

import com.arellomobile.mvp.MvpAppCompatActivity
import com.temp.utils.DialogFactory
import com.temp.utils.Utils


abstract class BaseActivity : MvpAppCompatActivity(), BaseMvpView {

    override fun showProgressDialog() {
        DialogFactory.showProgressDialog(this)
    }

    override fun hideProgressDialog() {
        DialogFactory.hideProgressDialog()
    }

    override fun hideKeyBoard() {
        Utils.hideKeyboard(this)
    }
}