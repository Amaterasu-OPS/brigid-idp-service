package com.brigid.idp.adapters.api.user.controllers

import com.brigid.idp.application.api.ControllerInterface
import com.brigid.idp.application.use_cases.user.UserVerifyCredentialCase
import com.brigid.idp.dto.user.verifyCredential.UserVerifyCredentialRequestDTO
import com.brigid.idp.dto.user.verifyCredential.UserVerifyCredentialResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class UserVerifyCredentialController(
    private val case: UserVerifyCredentialCase
) : ControllerInterface<UserVerifyCredentialRequestDTO, UserVerifyCredentialResponseDTO>() {
    override fun handler(body: UserVerifyCredentialRequestDTO): ResponseEntity<UserVerifyCredentialResponseDTO> {
        return ResponseEntity.status(HttpStatus.OK).body(case.handler(body))
    }
}