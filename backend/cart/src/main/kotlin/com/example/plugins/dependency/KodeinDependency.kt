package com.example.plugins.dependency

import com.example.plugins.repo.Repo
import com.example.plugins.repo.RepoImpl
import io.ktor.application.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton


fun Application.configureDependency(): Kodein {
    // sets up dependency in the kodein container
    val kodein = Kodein {
        bind<Repo>() with singleton { RepoImpl() }
    }
    return kodein
}