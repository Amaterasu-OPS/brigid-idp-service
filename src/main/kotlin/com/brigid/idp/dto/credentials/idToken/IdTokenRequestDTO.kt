package com.brigid.idp.dto.credentials.idToken

import java.util.UUID

data class IdTokenRequestDTO (
    val userId: UUID,
    val clientId: String,
    val scopes: List<String>,
)
