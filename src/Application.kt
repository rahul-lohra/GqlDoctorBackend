package com.rahul

import com.rahul.datasource.Server
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.locations.Locations
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.serialization.json
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    val server = Server()
    val port = System.getenv("PORT")?.toInt() ?: 8083
    val nettyServer = embeddedServer(Netty, port = port) {
        install(ContentNegotiation) {
            json()
        }
        install(Locations)
        routing {
            server.injectComponents(this)
            get("/") {
                call.respond(HttpStatusCode.OK, "Hello")
            }
        }
    }
    nettyServer.start(wait = true)
}
