package com.example.be_meet_jwt_demo

import com.example.be_meet_jwt_demo.property.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class)
class BeMeetJwtDemoApplication

fun main(args: Array<String>) {
    runApplication<BeMeetJwtDemoApplication>(*args)
}
