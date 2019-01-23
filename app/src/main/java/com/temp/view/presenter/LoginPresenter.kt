package com.temp.view.presenter

import android.support.annotation.NonNull
import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.temp.data.soap.OnSoapListener
import com.temp.domain.interactor.SoapUseCase
import com.temp.view.PHApplication
import com.temp.view.base.BasePresenter
import com.temp.view.view.LoginView
import java.lang.Exception
import javax.inject.Inject

@InjectViewState
class LoginPresenter : BasePresenter<LoginView>() {

    @Inject
    lateinit var soapUseCase: SoapUseCase

    init {
        PHApplication.getAppComponent().inject(this)
    }

    fun login(@NonNull email: String, @NonNull password: String) {
        viewState.showProgressDialog()

        GlobalScope.launch {
            soapUseCase.buildUseCase().login(email, password, listener = object : OnSoapListener {
                override fun onError(exception: Exception) {
                    viewState.hideProgressDialog()
                    viewState.showError(exception)
                }

                override fun onSuccess() {
                    viewState.hideProgressDialog()
                    viewState.navigateToMainScreen()
                }
            })
        }
    }

}