package com.example.be_meet_jwt_demo.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.example.be_meet_jwt_demo.property.JwtProperties
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Service
class JwtService(
        private val jwtProperties: JwtProperties
) {
    fun createToken(username: String): String {
        val now = Instant.now()
        return JWT.create()
                .withIssuer(jwtProperties.issuer)
                .withClaim("roles", listOf("ROLE_USER"))
                .withJWTId(UUID.randomUUID().toString())
                .withSubject(username)
                .withIssuedAt(now)
                .withNotBefore(now)
                .withExpiresAt(now.plus(1, ChronoUnit.HOURS))
                .sign(Algorithm.HMAC256(jwtProperties.hmac.secret))
    }

    fun parseToken(token: String): DecodedJWT {
        return JWT.require(Algorithm.HMAC256(jwtProperties.hmac.secret))
                .withIssuer(jwtProperties.issuer)
                .build()
                .verify(token)
    }
}