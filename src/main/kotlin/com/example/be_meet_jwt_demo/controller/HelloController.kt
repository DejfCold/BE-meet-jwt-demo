package com.example.be_meet_jwt_demo.controller

import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class HelloController {
    @GetMapping("/public")
    fun getPublic(): String {
        return "public"
    }

    @GetMapping("/private")
    fun getPrivate(): String {
        return "private"
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/private/admin")
    fun getPrivateAdmin(): String {
        return "private admin"
    }

    @Secured("ROLE_USER")
    @GetMapping("/private/user")
    fun getPrivateUser(): String {
        return "private user"
    }
}