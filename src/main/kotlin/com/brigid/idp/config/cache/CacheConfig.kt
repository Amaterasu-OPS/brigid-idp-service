package com.brigid.idp.config.cache

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "app.cache")
data class CacheConfig(
    var host: String = "localhost",
    var port: Int = 6379,
    var db: Int = 0
)