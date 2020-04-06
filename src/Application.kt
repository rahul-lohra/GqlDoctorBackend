package com.rahul

import com.rahul.datasource.Server
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    val server = Server()

    val nettyServer = embeddedServer(Netty, port = 8083) {
        routing {
            server.injectComponents(this)

            get("/") {
                call.respondText("Hello World!", ContentType.Text.Plain)
            }
            get("/demo") {
                call.respondText("HELLO WORLD!")
            }

        }
    }
    nettyServer.start(wait = true)
}
