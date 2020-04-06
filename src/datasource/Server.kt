package com.rahul.datasource

import com.rahul.di.DaggerAppComponent
import com.rahul.routes.v1.RoutePosts
import di.modules.RouteModule
import io.ktor.routing.Routing
import javax.inject.Inject


class Server {

    @Inject
    lateinit var routePosts: RoutePosts

    fun injectComponents(routing: Routing) {
        DaggerAppComponent.builder()
            .routeModule(RouteModule(routing))
            .build()
            .inject(this)

        //setup POJO conversion

    }
}