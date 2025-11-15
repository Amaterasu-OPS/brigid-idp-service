package com.brigid.idp.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "users")
data class UserModel(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column
    val name: String,

    @Column
    val familyName: String,

    @Column
    val email: String,

    @Column
    val password: String,

    @Column
    val birthDate: LocalDate,

    @Column
    val gender: String,

    @Column
    val status: Int = 1,

    @Column
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column
    val updatedAt: LocalDateTime = LocalDateTime.now()
)