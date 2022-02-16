package com.example.plugins.dependency

import com.example.plugins.database.Database
import com.example.plugins.database.DatabaseImpl
import com.example.plugins.repository.ApiService
import com.example.plugins.repository.ApiServiceImpl
import io.ktor.application.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

fun Application.configureDependency(): Kodein {
    // sets up dependency in the kodein container
    val kodein = Kodein {
        bind<Database>() with singleton { DatabaseImpl() }
        bind<ApiService>() with singleton { ApiServiceImpl(kodein) }
    }
    return kodein
}