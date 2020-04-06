package com.rahul.di

import com.rahul.datasource.Server
import com.rahul.di.modules.DatabaseModule
import com.rahul.di.modules.RepositoryModule
import dagger.Component
import di.modules.RouteModule
import io.ktor.routing.Routing

@Component(modules = [DatabaseModule::class, RepositoryModule::class, RouteModule::class])
interface AppComponent {
    fun inject(server: Server)

    val routing: Routing
}