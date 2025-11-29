package com.brigid.idp.application.use_cases.credentials

import com.brigid.idp.adapters.spi.repositories.IUserRepository
import com.brigid.idp.application.api.UseCaseInterface
import com.brigid.idp.dto.credentials.idToken.IdTokenRequestDTO
import com.brigid.idp.dto.credentials.idToken.IdTokenResponseDTO
import com.brigid.idp.exceptions.DatabaseApiException
import com.brigid.idp.models.UserModel
import com.brigid.idp.utils.JwtGenerate
import org.springframework.stereotype.Component
import java.util.UUID


@Component
class IdTokenCase(
    private val repository: IUserRepository,
    private val jwt: JwtGenerate,
): UseCaseInterface<IdTokenRequestDTO, IdTokenResponseDTO, UserModel>(repository) {
    override fun handler(body: IdTokenRequestDTO): IdTokenResponseDTO {
        val userResult = try {
            repository.findById(body.userId)
        } catch (_: Exception) {
            throw DatabaseApiException("User with id '${body.userId}' could not be found", 404)
        }

        if (userResult.isEmpty) {
            throw DatabaseApiException("User with id '${body.userId}' could not be found", 404)
        }

        val user = userResult.get()

        val token = try {
            jwt.createToken(
                user.id.toString(),
                5L,
                this.prepareClaims(body, user),
            )
        } catch (_: Exception) {
            throw DatabaseApiException("Could not generate ID token for user with id '${body.userId}'", 500)
        }

        return IdTokenResponseDTO(
            token,
        )
    }

    fun prepareClaims(
        body: IdTokenRequestDTO,
        user: UserModel,
    ): Map<String, Any> {
        val claims = mutableMapOf<String, Any>(
            "nonce" to UUID.randomUUID().toString(),
        )

        if (body.scopes.contains("profile")) {
            claims["given_name"] = user.name
            claims["family_name"] = user.familyName
            claims["birthdate"] = user.birthDate.toString()
            claims["gender"] = user.gender
            claims["created_at"] = user.createdAt.toString()
            claims["updated_at"] = user.updatedAt.toString()
        }

        if (body.scopes.contains("email")) {
            claims["email"] = user.email
        }

        return claims
    }
}