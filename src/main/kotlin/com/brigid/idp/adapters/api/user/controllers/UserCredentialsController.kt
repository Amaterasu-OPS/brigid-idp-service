package com.brigid.idp.adapters.api.user.controllers

import com.brigid.idp.application.api.ControllerInterface
import com.brigid.idp.application.use_cases.user.UserCredentialsCase
import com.brigid.idp.dto.user.credentials.UserCredentialsRequestDTO
import com.brigid.idp.dto.user.credentials.UserCredentialsResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class UserCredentialsController(
    private val case: UserCredentialsCase
): ControllerInterface<UserCredentialsRequestDTO, UserCredentialsResponseDTO>() {
    override fun handler(body: UserCredentialsRequestDTO): ResponseEntity<UserCredentialsResponseDTO> {
        return ResponseEntity.status(HttpStatus.OK).body(case.handler(body))
    }
}