package com.brigid.idp.application.use_cases.user

import com.brigid.idp.adapters.spi.mapper.UserMapper
import com.brigid.idp.adapters.spi.repositories.IUserRepository
import com.brigid.idp.application.api.UseCaseInterface
import com.brigid.idp.dto.user.credentials.UserCredentialsRequestDTO
import com.brigid.idp.dto.user.credentials.UserCredentialsResponseDTO
import com.brigid.idp.exceptions.ApiException
import com.brigid.idp.exceptions.DatabaseApiException
import com.brigid.idp.models.UserModel
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.security.MessageDigest
import org.springframework.stereotype.Component
import java.util.Base64
import java.util.UUID
import java.util.concurrent.TimeUnit

@Component
class UserCredentialsCase(
    private val repository: IUserRepository,
    private val mapper: UserMapper,
    private val cache:  RedisTemplate<String, Any>,
    private val passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder(14),
    private val objectMapper: ObjectMapper,
) : UseCaseInterface<UserCredentialsRequestDTO, UserCredentialsResponseDTO, UserModel>(repository) {
    override fun handler(body: UserCredentialsRequestDTO): UserCredentialsResponseDTO {
        val user = try {
            repository.findByEmail(body.email)
        } catch (_: Exception) {
            throw DatabaseApiException("Error on fetching user by email", 500)
        }

        if (user == null) {
            throw ApiException("User not found", 404)
        }

        if (!passwordEncoder.matches(body.password, user.password)) {
            throw ApiException("Invalid credentials", 401)
        }

        val digest = MessageDigest.getInstance("SHA-256")
        val token = Base64.getEncoder().encodeToString(digest.digest(UUID.randomUUID().toString().toByteArray()))
        val userEntity = mapper.modelToEntity(user)
        val result = objectMapper.writeValueAsString(userEntity)

        val redis = cache.opsForValue()

        try {
            redis.set(token, result, 3600L, TimeUnit.SECONDS)
        } catch (_: Exception) {
            throw DatabaseApiException("Error on saving token to cache", 500)
        }

        return UserCredentialsResponseDTO(
            userId = user.id,
            authToken = token,
            expiresIn = 3600L,
        )
    }
}