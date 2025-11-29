package com.brigid.idp.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "app.apikey")
data class ApiKeysConfig(
    var idToken: String = "",
)