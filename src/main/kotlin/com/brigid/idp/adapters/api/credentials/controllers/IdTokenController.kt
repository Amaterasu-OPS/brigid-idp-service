package com.brigid.idp.adapters.api.credentials.controllers

import com.brigid.idp.application.api.ControllerInterface
import com.brigid.idp.application.use_cases.credentials.IdTokenCase
import com.brigid.idp.config.ApiKeysConfig
import com.brigid.idp.dto.credentials.idToken.IdTokenRequestDTO
import com.brigid.idp.dto.credentials.idToken.IdTokenResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class IdTokenController(
    private val case: IdTokenCase,
    private val apiKeysConfig: ApiKeysConfig
): ControllerInterface<IdTokenRequestDTO, IdTokenResponseDTO>() {
    override fun handler(body: IdTokenRequestDTO): ResponseEntity<IdTokenResponseDTO> {
        return ResponseEntity.status(HttpStatus.OK).body(case.handler(body))
    }

    fun execute(body: IdTokenRequestDTO, apiKey: String): ResponseEntity<IdTokenResponseDTO> {
        if (apiKey != apiKeysConfig.idToken) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
        
        return this.handler(body)
    }
}