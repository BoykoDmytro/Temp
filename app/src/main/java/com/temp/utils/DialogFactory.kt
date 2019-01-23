package com.temp.utils

import android.app.Dialog
import android.content.Context
import android.support.v7.app.AlertDialog
import com.temp.R
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.support.annotation.StringRes

object DialogFactory {
    private var progressDialog: AlertDialog? = null

    fun showProgressDialog(context: Context) {
        hideProgressDialog()
        setDialog(context)
        progressDialog!!.show()
    }

    fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) progressDialog!!.dismiss()
    }

    private fun setDialog(context: Context) {
        if (progressDialog == null) {
            val builder = AlertDialog.Builder(context)
            builder.setView(R.layout.progress_layout)
            progressDialog = builder.create()
        }
    }

    fun createOkNoDialog(
        context: Context, messageId: Int,
        yesClickListener: DialogInterface.OnClickListener?,
        noClickListener: DialogInterface.OnClickListener?
    ): Dialog {
        return createDialog(
            context,
            messageId,
            android.R.string.ok,
            android.R.string.no,
            yesClickListener,
            noClickListener
        )
    }

    private fun createDialog(
        context: Context, @StringRes messageId: Int,
        @StringRes positiveBtn: Int, @StringRes negativeBtn: Int,
        positiveClickListener: DialogInterface.OnClickListener?,
        negativeClickListener: DialogInterface.OnClickListener?
    ): Dialog {
        if (context !is AppCompatActivity) {
            throw IllegalStateException("This context isn't instance of activity")
        }
        val builder = AlertDialog.Builder(context)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setMessage(messageId)
        if (positiveClickListener != null)
            builder.setPositiveButton(positiveBtn, positiveClickListener)
        if (negativeClickListener != null)
            builder.setNegativeButton(negativeBtn, negativeClickListener)

        return builder.create()
    }
}