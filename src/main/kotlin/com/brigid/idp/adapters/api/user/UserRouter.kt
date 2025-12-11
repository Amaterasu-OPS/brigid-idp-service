package com.brigid.idp.adapters.api.user

import com.brigid.idp.adapters.api.user.controllers.UserAddController
import com.brigid.idp.adapters.api.user.controllers.UserCredentialsController
import com.brigid.idp.adapters.api.user.controllers.UserVerifyCredentialController
import com.brigid.idp.dto.user.add.UserAddRequestDTO
import com.brigid.idp.dto.user.add.UserAddResponseDTO
import com.brigid.idp.dto.user.credentials.UserCredentialsRequestDTO
import com.brigid.idp.dto.user.credentials.UserCredentialsResponseDTO
import com.brigid.idp.dto.user.verifyCredential.UserVerifyCredentialRequestDTO
import com.brigid.idp.dto.user.verifyCredential.UserVerifyCredentialResponseDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserRouter(
    private val userAddController: UserAddController,
    private val userCredentialsController: UserCredentialsController,
    private val userVerifyCredentialController: UserVerifyCredentialController,
) {
    @PostMapping("/add")
    fun userAddRoute(
        @RequestBody data: UserAddRequestDTO,
    ): ResponseEntity<UserAddResponseDTO> = userAddController.handler(data)

    @PostMapping("/credentials")
    fun userCredentialsRoute(
        @RequestBody data: UserCredentialsRequestDTO,
    ): ResponseEntity<UserCredentialsResponseDTO> = userCredentialsController.handler(data)

    @PostMapping("/verify-credential")
    fun userVerifyCredential(
        @RequestBody data: UserVerifyCredentialRequestDTO,
    ): ResponseEntity<UserVerifyCredentialResponseDTO> = userVerifyCredentialController.handler(data)
}