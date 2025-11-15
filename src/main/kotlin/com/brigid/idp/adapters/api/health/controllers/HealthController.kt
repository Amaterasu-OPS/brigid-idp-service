package com.brigid.idp.adapters.api.health.controllers

import com.brigid.idp.application.api.ControllerInterface
import com.brigid.idp.application.use_cases.health.HealthCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class HealthController(
    private val case: HealthCase
): ControllerInterface<Any, String>() {
    override fun handler(body: Any): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.OK).body(case.handler(body));
    }
}