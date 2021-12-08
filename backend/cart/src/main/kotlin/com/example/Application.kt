package com.example

import com.example.plugins.configureMonitoring
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.plugins.dependency.configureDependency
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 5003, host = "localhost") {
        val kodein = configureDependency()
        configureRouting(kodein)
        configureSerialization()
        configureMonitoring()

//        configureHTTP()
//        configureSecurity()
    }.start(wait = true)
}
