package com.brigid.idp.adapters.api.credentials

import com.brigid.idp.adapters.api.credentials.controllers.IdTokenController
import com.brigid.idp.dto.credentials.idToken.IdTokenRequestDTO
import com.brigid.idp.dto.credentials.idToken.IdTokenResponseDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/credentials")
class CredentialsRouter(
    private val idTokenController: IdTokenController,
) {
    @PostMapping("/id_token")
    fun idTokenRoute(
        @RequestBody data: IdTokenRequestDTO,
        @RequestHeader("X-Api-key") apiKey: String
    ): ResponseEntity<IdTokenResponseDTO> = idTokenController.execute(data, apiKey)
}