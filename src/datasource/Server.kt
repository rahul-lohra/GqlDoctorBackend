package com.rahul.datasource

import com.rahul.di.DaggerAppComponent
import com.rahul.routes.v1.RouteGql
import com.rahul.routes.v1.RoutePosts
import com.rahul.routes.v1.RouteRest
import di.modules.RouteModule
import io.ktor.routing.Routing
import javax.inject.Inject


class Server {

    @Inject
    lateinit var routePosts: RoutePosts

    @Inject
    lateinit var routeGql: RouteGql

    @Inject
    lateinit var routeRest: RouteRest

    fun injectComponents(routing: Routing) {
        DaggerAppComponent.builder()
            .routeModule(RouteModule(routing))
            .build()
            .inject(this)

    }
}