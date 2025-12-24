package com.brigid.idp.dto.user.get

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class UserGetResponseDTO(
    val id: UUID,
    val name: String,
    val familyName: String,
    val birthDate: LocalDate,
    val gender: String,
    val email: String,
    val status: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)