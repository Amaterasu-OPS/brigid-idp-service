package com.brigid.idp.dto.user.add

import java.time.LocalDate

enum class Gender { M, F, N }

data class UserAddRequestDTO(
    val name: String,
    val familyName: String,
    val email: String,
    val password: String,
    val birthDate: LocalDate,
    val gender: Gender
)