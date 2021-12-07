package com.example

import com.example.plugins.configureMonitoring
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 5003, host = "localhost") {
        configureRouting()
        configureSerialization()
        configureMonitoring()
//        configureHTTP()
//        configureSecurity()
    }.start(wait = true)
}
