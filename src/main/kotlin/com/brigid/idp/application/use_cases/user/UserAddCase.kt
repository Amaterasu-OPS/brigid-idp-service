package com.brigid.idp.application.use_cases.user

import com.brigid.idp.adapters.spi.mapper.UserMapper
import com.brigid.idp.adapters.spi.repositories.IUserRepository
import com.brigid.idp.application.api.UseCaseInterface
import com.brigid.idp.domain.User
import com.brigid.idp.dto.user.UserAddRequestDTO
import com.brigid.idp.exceptions.DatabaseException
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
        var user: UserModel? = null

        try {
            user = repository.save(UserModel(
                name = body.name,
                familyName = body.familyName,
                email = body.email,
                password = passwordEncoder.encode(body.password),
                birthDate = body.birthDate,
                gender = body.gender.toString(),
            ))
        } catch (e: Exception) {
            throw DatabaseException("Error on saving user")
        }

        return mapper.modelToEntity(user)
    }
}