package com.rahul

import com.rahul.datasource.Server
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.locations.Locations
import io.ktor.routing.routing
import io.ktor.serialization.json
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    val server = Server()

    val nettyServer = embeddedServer(Netty, port = 8083) {
        install(ContentNegotiation) {
            json()
        }
        install(Locations)
        routing {
            server.injectComponents(this)
        }
    }
    nettyServer.start(wait = true)
}
