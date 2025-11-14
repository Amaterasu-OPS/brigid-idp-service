package com.brigid.idp.adapters.api.health

import com.brigid.idp.adapters.api.health.controllers.HealthController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/health")
class HealthRouter() {
    @GetMapping
    fun healthRoute(controller: HealthController): ResponseEntity<String> = controller.handler("");
}