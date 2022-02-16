package com.example

import com.example.plugins.configureMonitoring
import com.example.plugins.configureSerialization
import com.example.plugins.database.initDB
import com.example.plugins.dependency.configureDependency
import com.example.plugins.routes.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 5003) {

        val kodein = configureDependency()
        initDB(kodein, environment)
        configureRouting(kodein)
        configureSerialization()
        configureMonitoring()

//        configureHTTP()
//        configureSecurity()
    }.start(wait = true)
}
