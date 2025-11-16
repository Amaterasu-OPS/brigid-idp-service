package com.brigid.idp.dto.user.credentials

import java.util.UUID

data class UserCredentialsResponseDTO(
    val userId: UUID,
    val authToken: String,
    val expiresIn: Long
)