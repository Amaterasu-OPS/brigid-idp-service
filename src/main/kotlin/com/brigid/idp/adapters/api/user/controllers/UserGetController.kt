package com.brigid.idp.adapters.api.user.controllers

import com.brigid.idp.application.api.ControllerInterface
import com.brigid.idp.application.use_cases.user.UserGetCase
import com.brigid.idp.dto.user.get.UserGetResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import java.util.UUID

@Controller
class UserGetController(
    private val case: UserGetCase
): ControllerInterface<UUID, UserGetResponseDTO>() {
    override fun handler(body: UUID): ResponseEntity<UserGetResponseDTO> {
        return ResponseEntity.status(HttpStatus.OK).body(case.handler(body))
    }
}