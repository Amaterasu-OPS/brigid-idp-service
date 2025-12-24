package com.brigid.idp.application.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

interface IController<Body, Response> {
    fun handler(@RequestBody body: Body): ResponseEntity<Response>
}

abstract class ControllerInterface<Body, Response>: IController<Body, Response>
