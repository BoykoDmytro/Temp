package com.temp.data.soap

import org.ksoap2.serialization.SoapSerializationEnvelope
import org.xmlpull.v1.XmlSerializer

import java.io.IOException
import java.util.*

class PHSoapEnvelope(version: Int) : SoapSerializationEnvelope(version) {

    private val ID_LABEL = "id"
    private val ROOT_LABEL = "root"
    private val TYPE_LABEL = "type"
    private val ITEM_LABEL = "item"

    var xsm: String = "http://schemas.microsoft.com/exchange/services/2006/messages"
    var xst: String = "http://schemas.microsoft.com/exchange/services/2006/types"


    @Throws(IOException::class)
    override fun write(writer: XmlSerializer) {
        writer.setPrefix("v", this.env)
        writer.setPrefix("t", this.xst)
        writer.setPrefix("m", this.xsm)
        writer.startTag(this.env, "Envelope")
        writer.startTag(this.env, "Header")
        this.writeHeader(writer)
        writer.endTag(this.env, "Header")
        writer.startTag(this.env, "Body")
        this.writeBody(writer)
        writer.endTag(this.env, "Body")
        writer.endTag(this.env, "Envelope")
    }

    @Throws(IOException::class)
    override fun writeBody(writer: XmlSerializer) {
        // allow an empty body without any tags in it
        // see http://code.google.com/p/ksoap2-android/issues/detail?id=77
        val field = SoapSerializationEnvelope::class.java.getDeclaredField("multiRef")
        field.isAccessible = true

        if (bodyOut != null) {
            val multiRef = Vector<Any>()
            multiRef.addElement(bodyOut)
            field.set(this, multiRef)
            val qName = getInfo(null, bodyOut)
            writer.startTag(if (dotNet) "" else qName[QNAME_NAMESPACE] as String, qName[QNAME_TYPE] as String)
            if (!addAdornments) {
                writer.attribute(null, ID_LABEL, if (qName[2] == null) "o" + 0 else qName[2] as String)
                writer.attribute(enc, ROOT_LABEL, "1")
            }
            writeElement(writer, bodyOut, null, qName[QNAME_MARSHAL])
            writer.endTag(if (dotNet) "" else qName[QNAME_NAMESPACE] as String, qName[QNAME_TYPE] as String)
        }
    }
}
