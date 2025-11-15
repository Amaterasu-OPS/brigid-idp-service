package com.brigid.idp.adapters.spi.mapper

import com.brigid.idp.domain.User
import com.brigid.idp.models.UserModel
import org.springframework.stereotype.Service

@Service
class UserMapper {
    fun modelToEntity(model: UserModel): User {
        return User(
            id = model.id,
            name = model.name,
            familyName = model.familyName,
            email = model.email,
            birthDate = model.birthDate,
            gender = model.gender,
            updatedAt = model.updatedAt,
            createdAt = model.createdAt,
            status = model.status,
        );
    }
}