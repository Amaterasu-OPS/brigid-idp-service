package com.brigid.idp.application.use_cases.user

import com.brigid.idp.adapters.spi.mapper.UserMapper
import com.brigid.idp.adapters.spi.repositories.IUserRepository
import com.brigid.idp.application.api.UseCaseInterface
import com.brigid.idp.domain.User
import com.brigid.idp.dto.user.add.UserAddRequestDTO
import com.brigid.idp.exceptions.DatabaseApiException
import com.brigid.idp.models.UserModel
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserAddCase(
    private val repository: IUserRepository,
    private val mapper: UserMapper,
    private val passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder(14)
): UseCaseInterface<UserAddRequestDTO, User, UserModel>(repository) {
    override fun handler(body: UserAddRequestDTO): User {
        var existingUser: UserModel?

        try {
            existingUser = repository.findByEmail(body.email)
        } catch (e: Exception) {
            throw DatabaseApiException("Error on checking existing user", 500)
        }

        if (existingUser != null) {
            throw DatabaseApiException("User with email ${body.email} already exists", 400)
        }

        try {
            val user = repository.save(UserModel(
                name = body.name,
                familyName = body.familyName,
                email = body.email,
                password = passwordEncoder.encode(body.password),
                birthDate = body.birthDate,
                gender = body.gender.toString(),
            ))

            return mapper.modelToEntity(user)
        } catch (e: Exception) {
            throw DatabaseApiException("Error on saving user", 500)
        }
    }
}