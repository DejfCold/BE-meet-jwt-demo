package com.example.be_meet_jwt_demo.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "be-meet.jwt")
data class JwtProperties (
        val issuer: String,
        val hmac: Hmac,
) {
    data class Hmac (
            val secret: String
    )
}