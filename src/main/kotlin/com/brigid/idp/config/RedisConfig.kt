package com.brigid.idp.config

import com.brigid.idp.config.cache.CacheConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig(
    private val config: CacheConfig
) {
    @Bean
    fun redisConn(): JedisConnectionFactory {
        val redis = RedisStandaloneConfiguration()

        redis.port = config.port
        redis.hostName = config.host
        redis.database = config.db

        return JedisConnectionFactory(redis)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()

        redisTemplate.connectionFactory = redisConn()
        redisTemplate.stringSerializer = StringRedisSerializer()
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = StringRedisSerializer()
        redisTemplate.hashKeySerializer = StringRedisSerializer()
        redisTemplate.hashValueSerializer = StringRedisSerializer()

        return redisTemplate
    }
}