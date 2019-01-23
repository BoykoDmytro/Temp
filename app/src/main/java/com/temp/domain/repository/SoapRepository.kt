package com.temp.domain.repository

import android.support.annotation.NonNull
import com.temp.data.soap.OnSoapListener

interface SoapRepository : BaseRepository{

    fun login(@NonNull email: String, @NonNull password: String, @NonNull listener: OnSoapListener)

    fun search(@NonNull query: String, @NonNull listener: OnSoapListener)
}