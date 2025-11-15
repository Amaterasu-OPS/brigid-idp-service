package com.brigid.idp.application.api

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import java.util.UUID

interface IController<Body, Response> {
    fun handler(@RequestBody body: Body): ResponseEntity<Response>
}

abstract class ControllerInterface<Body, Response>: IController<Body, Response>
