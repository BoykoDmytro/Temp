package com.temp.domain.interactor

import com.temp.domain.repository.BaseRepository

abstract class BaseUseCase<T: BaseRepository> {

    abstract fun buildUseCase() :T
}