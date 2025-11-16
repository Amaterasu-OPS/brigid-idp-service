package com.brigid.idp.domain

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class User(
    val id: UUID,
    val name: String,
    val familyName: String,
    val birthDate: LocalDate,
    val gender: String,
    val email: String,
    val status: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
): Serializable
