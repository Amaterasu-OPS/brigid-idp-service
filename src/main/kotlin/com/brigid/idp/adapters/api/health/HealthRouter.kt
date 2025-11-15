package com.brigid.idp.adapters.api.health

import com.brigid.idp.adapters.api.health.controllers.HealthController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/health")
class HealthRouter(
    private val controller: HealthController
) {
    @GetMapping
    fun healthRoute(): ResponseEntity<String> = controller.handler("");
}