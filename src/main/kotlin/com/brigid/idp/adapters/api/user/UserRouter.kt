package com.brigid.idp.adapters.api.user

import com.brigid.idp.adapters.api.user.controllers.UserAddController
import com.brigid.idp.dto.user.UserAddRequestDTO
import com.brigid.idp.dto.user.UserAddResponseDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserRouter(
    private val userAddController: UserAddController
) {
    @PostMapping("/add")
    fun userAddRoute(
        @RequestBody data: UserAddRequestDTO,
    ): ResponseEntity<UserAddResponseDTO> = userAddController.handler(data)
}