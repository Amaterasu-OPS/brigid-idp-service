package com.brigid.idp.application.api

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

interface IController<T, V> {
    fun handler(@RequestBody body: T): ResponseEntity<V>
}

abstract class ControllerInterface<T, V, U>(
    repository: JpaRepository<U, String>?
): IController<T, V>

