package com.brigid.idp.utils

import com.brigid.idp.config.JwtConfig
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date

@Component
class JwtGenerate(
    private val jwtConfig: JwtConfig,
) {
    fun createToken(
        subject: String,
        expirationMinutes: Long,
        claims: Map<String, Any>,
    ): String {
        return Jwts.builder()
            .subject(subject)
            .claims(claims)
            .issuer(jwtConfig.issuer)
            .issuedAt(Date.from(Instant.now()))
            .expiration(Date.from(Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES)))
            .signWith(jwtConfig.privateKey)
            .compact()
    }

    fun verifyToken(token: String): Boolean {
        return try {
            Jwts.parser()
                .verifyWith(jwtConfig.publicKey)
                .requireIssuer(jwtConfig.issuer)
                .build()
                .parse(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getClaims(token: String): Claims? {
        return try {
            val jwt = Jwts.parser()
                .verifyWith(jwtConfig.publicKey)
                .requireIssuer(jwtConfig.issuer)
                .build()
                .parse(token)
            jwt.payload
        } catch (e: Exception) {
            null
        } as Claims?
    }
}