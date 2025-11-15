package com.brigid.idp.application.api

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IUseCase<T, V> {
    fun handler(body: T): V
}

abstract class UseCaseInterface<T, V, U>(repository: JpaRepository<U, UUID>?): IUseCase<T, V>