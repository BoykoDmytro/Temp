package com.temp.view.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import android.widget.TextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.temp.R
import com.temp.utils.DialogFactory
import com.temp.view.base.BaseActivity
import com.temp.view.customview.EmailValidator
import com.temp.view.presenter.LoginPresenter
import com.temp.view.view.LoginView

class LoginActivity : BaseActivity(), LoginView {

    lateinit var edtEmail: TextInputEditText
    lateinit var edtPassword: TextInputEditText
    lateinit var btnLogin: FrameLayout

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener { login() }
        edtEmail.addTextChangedListener(EmailValidator(edtEmail))
        edtPassword.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent?.keyCode == KeyEvent.KEYCODE_ENTER) {
                login()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun login() {
        hideKeyBoard()
        presenter.login(edtEmail.text.toString(), edtPassword.text.toString())
    }

    override fun navigateToMainScreen() {
        startActivity(MainActivity.getInstance(this))
    }

    override fun showError(exception: Exception) {
        DialogFactory.createOkNoDialog(this, R.string.login_error,
            DialogInterface.OnClickListener { _: DialogInterface, _: Int -> }, null
        ).show()
    }
}