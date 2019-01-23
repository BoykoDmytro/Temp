package com.temp.data.soap

interface OnSoapListener {
    fun onSuccess()

    fun onError(exception: java.lang.Exception)
}