package com.example.be_meet_jwt_demo.filters

import com.example.be_meet_jwt_demo.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Order(0)
@Component
class JwtFilter(
        private val jwtService: JwtService
) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authorization = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (authorization == null) {
            filterChain.doFilter(request, response)
            return
        }

        val splitAuth = authorization.split(" ")
        if (splitAuth.size != 2 && splitAuth[0].lowercase() != "bearer") {
            filterChain.doFilter(request, response)
            return
        }

        val token = splitAuth[1]
        val jwt = try {
            jwtService.parseToken(token)
        } catch (ex: Exception) {
            println(ex.message)
            filterChain.doFilter(request, response)
            return
        }

        val username = jwt.subject
        val roles = jwt.getClaim("roles").asList(String::class.java)

        println("Username: ${username}; Roles: ${roles.joinToString(",")}")

        val authorities = roles.map { SimpleGrantedAuthority(it) }

        val authentication = UsernamePasswordAuthenticationToken.authenticated(username, null, authorities)
        val context = SecurityContextHolder.createEmptyContext()
        context.authentication = authentication
        SecurityContextHolder.setContext(context)

        filterChain.doFilter(request, response)
    }
}