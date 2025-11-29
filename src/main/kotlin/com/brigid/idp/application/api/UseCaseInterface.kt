package com.brigid.idp.application.api

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IUseCase<Body, Response> {
    fun handler(body: Body): Response
}

abstract class UseCaseInterface<Body, Response, Repository>(repository: JpaRepository<Repository, UUID>?): IUseCase<Body, Response>