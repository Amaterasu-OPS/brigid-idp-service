package com.brigid.idp.application.api

import org.springframework.data.jpa.repository.JpaRepository

interface IUseCase<T, V> {
    fun handler(body: T): V
}

abstract class UseCaseInterface<T, V, U>(repository: JpaRepository<U, String>?): IUseCase<T, V>