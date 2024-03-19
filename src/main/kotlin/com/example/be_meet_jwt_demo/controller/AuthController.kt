package com.example.be_meet_jwt_demo.controller

import com.example.be_meet_jwt_demo.dto.LoginDto
import com.example.be_meet_jwt_demo.service.JwtService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class AuthController(
        private val jwtService: JwtService,
) {
    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): String {
        return jwtService.createToken(loginDto.username)
    }

}