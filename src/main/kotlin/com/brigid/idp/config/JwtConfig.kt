package com.brigid.idp.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

@Component
class JwtConfig(
    @param:Value("\${app.jwt.privateKey:''}")
    private val privateKeyValue: String,
    @param:Value("\${app.jwt.publicKey:''}")
    private val publicKeyValue: String,
    @param:Value("\${app.jwt.issuer:''}")
    private val issuerValue: String,
) {
    val privateKey: PrivateKey by lazy { loadPrivateKey(privateKeyValue) }
    val publicKey: PublicKey by lazy { loadPublicKey(publicKeyValue) }
    val issuer: String by lazy { issuerValue }

    private fun loadPrivateKey(pk: String): PrivateKey {
        val keyContent = pk
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replace("\n", "")

        val decodedKey = Base64.getDecoder().decode(keyContent)
        val keySpec = PKCS8EncodedKeySpec(decodedKey)

        return KeyFactory.getInstance("RSA").generatePrivate(keySpec)
    }

    private fun loadPublicKey(pub: String): PublicKey {
        val keyContent = pub
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")
            .replace("\n", "")

        val decodedKey = Base64.getDecoder().decode(keyContent)
        val keySpec = X509EncodedKeySpec(decodedKey)
        return KeyFactory.getInstance("RSA").generatePublic(keySpec)
    }
}