package com.brigid.idp.application.use_cases.user

import com.brigid.idp.application.api.UseCaseInterface
import com.brigid.idp.dto.user.verifyCredential.UserVerifyCredentialRequestDTO
import com.brigid.idp.dto.user.verifyCredential.UserVerifyCredentialResponseDTO
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class UserVerifyCredentialCase(
    private val cache:  RedisTemplate<String, Any>,
): UseCaseInterface<UserVerifyCredentialRequestDTO, UserVerifyCredentialResponseDTO, Any>(null) {
    override fun handler(body: UserVerifyCredentialRequestDTO): UserVerifyCredentialResponseDTO {
        val redis = cache.opsForValue()

        val result = try {
            redis.get(body.token)
        } catch (_: Exception) {
            return UserVerifyCredentialResponseDTO(
                verified = false,
            )
        }

        if (result == null) {
            return UserVerifyCredentialResponseDTO(
                verified = false,
            )
        }

        try {
            cache.delete(body.token)
        } catch (_: Exception) {
            return UserVerifyCredentialResponseDTO(
                verified = false,
            )
        }

        return UserVerifyCredentialResponseDTO(
            verified = true,
        )
    }
}