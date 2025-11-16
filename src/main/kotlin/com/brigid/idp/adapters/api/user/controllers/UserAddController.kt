package com.brigid.idp.adapters.api.user.controllers

import com.brigid.idp.application.api.ControllerInterface
import com.brigid.idp.application.use_cases.user.UserAddCase
import com.brigid.idp.dto.user.add.UserAddRequestDTO
import com.brigid.idp.dto.user.add.UserAddResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class UserAddController(
    private val case: UserAddCase
): ControllerInterface<UserAddRequestDTO, UserAddResponseDTO>() {
    override fun handler(body: UserAddRequestDTO): ResponseEntity<UserAddResponseDTO> {
        val result = case.handler(body)

        return ResponseEntity.status(HttpStatus.CREATED).body(UserAddResponseDTO(
            id = result.id,
            name = result.name,
            familyName = result.familyName,
            birthDate = result.birthDate,
            gender = result.gender,
            email = result.email,
            status = result.status,
            createdAt = result.createdAt,
            updatedAt = result.updatedAt,
        ))
    }
}