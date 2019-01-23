package com.temp.domain.interactor

import com.temp.data.soap.SoapAPI
import com.temp.domain.repository.SoapRepository

class SoapUseCase : BaseUseCase<SoapRepository>(){

    override fun buildUseCase(): SoapRepository {
        return SoapAPI()
    }

}