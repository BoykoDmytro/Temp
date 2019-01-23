package com.temp.data.soap

import android.os.Handler
import android.os.Looper
import android.support.annotation.NonNull
import android.util.Base64
import org.ksoap2.HeaderProperty
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.transport.HttpTransportSE
import org.kxml2.kdom.Element
import com.temp.domain.repository.SoapRepository
import timber.log.Timber

class SoapAPI : SoapRepository {

    companion object {
        const val API_URL = "https://mail.nhs.net/ews/exchange.asmx"
        const val LOGIN_ACTION = "LOGIN"
    }

    private var handler: Handler = Handler(Looper.getMainLooper())

    override fun login(@NonNull email: String, @NonNull password: String, @NonNull listener: OnSoapListener) {
        val request = SoapObject("", "m:FindPeople")
        val findPeople = SoapObject("", "m:IndexedPageItemView")
        findPeople.addAttribute("BasePoint", "Beginning")
        findPeople.addAttribute("MaxEntriesReturned", "50")
        findPeople.addAttribute("Offset", "0")
        val parent = SoapObject("", "m:ParentFolderId")
        val dist = SoapObject("", "t:DistinguishedFolderId")
        dist.addAttribute("Id", "contacts")
        parent.addSoapObject(dist)
        request.addSoapObject(findPeople)
        request.addSoapObject(parent)

        val envelope = PHSoapEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(request)
        envelope.headerOut = arrayOfNulls<Element>(1)
        envelope.headerOut[0] = buildAuthHeader()
        envelope.isAddAdornments = true
        envelope.implicitTypes = true
        envelope.dotNet = false

        val headers: MutableList<HeaderProperty> = mutableListOf()
        val credentials = "$email:$password"
        val base64EncodedCredentials = Base64.encodeToString(credentials.toByteArray(), Base64.DEFAULT)
        headers.add(HeaderProperty("Authorization", "Basic $base64EncodedCredentials"))
        val androidHttpTransport = HttpTransportSE(API_URL)
        androidHttpTransport.debug = true
        try {
            androidHttpTransport.call(LOGIN_ACTION, envelope, headers)
            val resultsRequestSOAP = envelope.bodyIn
            handler.post { listener.onSuccess() }
            Timber.tag(this.javaClass.simpleName).d("Response::%s", resultsRequestSOAP.toString())
        } catch (e: Exception) {
            Timber.tag(this.javaClass.simpleName).e("Error: $e")
            handler.post { listener.onError(e) }
        }
    }

    private fun buildAuthHeader(): Element {
        val h = Element().createElement("", "t:RequestServerVersion")
        h.setAttribute("", "Version", "Exchange2013_SP1")
        return h
    }

    override fun search(query: String, @NonNull listener: OnSoapListener) {
    }
}