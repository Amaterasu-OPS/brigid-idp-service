package com.brigid.idp.adapters.spi.repositories

import com.brigid.idp.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IUserRepository: JpaRepository<UserModel, UUID> {
    fun findByEmail(email: String): UserModel?
}