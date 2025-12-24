package com.brigid.idp.application.use_cases.user

import com.brigid.idp.adapters.spi.mapper.UserMapper
import com.brigid.idp.adapters.spi.repositories.IUserRepository
import com.brigid.idp.application.api.UseCaseInterface
import com.brigid.idp.models.UserModel
import com.brigid.idp.dto.user.get.UserGetResponseDTO
import com.brigid.idp.exceptions.ApiException
import com.brigid.idp.exceptions.DatabaseApiException
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserGetCase(
    private val repository: IUserRepository,
    private val mapper: UserMapper,
): UseCaseInterface<UUID, UserGetResponseDTO, UserModel>(repository) {
    override fun handler(body: UUID): UserGetResponseDTO {
        val user = try {
            repository.findById(body)
        } catch (_: Exception) {
            throw DatabaseApiException("Error on fetching user", 500)
        }

        if (user.isEmpty) {
            throw ApiException("User with id $body not found", 404)
        }

        val mappedUser = mapper.modelToEntity(user.get())

        return UserGetResponseDTO(
            id = mappedUser.id,
            name = mappedUser.name,
            familyName = mappedUser.familyName,
            email = mappedUser.email,
            birthDate = mappedUser.birthDate,
            gender = mappedUser.gender,
            status = mappedUser.status,
            createdAt = mappedUser.createdAt,
            updatedAt = mappedUser.updatedAt,
        )
    }
}