package com.brigid.idp.adapters.api.health.controllers

import com.brigid.idp.application.api.ControllerInterface
import com.brigid.idp.application.use_cases.health.HealthCase
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class HealthController(): ControllerInterface<Any, String, Any>(null) {
    override fun handler(body: Any): ResponseEntity<String> {
        val case = HealthCase();
        return ResponseEntity.status(200).body(case.handler(body));
    }
}