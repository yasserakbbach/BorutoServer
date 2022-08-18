package com.yasserakbbach.borutoserver.plugins

import com.yasserakbbach.borutoserver.routes.getAllHeroes
import com.yasserakbbach.borutoserver.routes.root
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()
        getAllHeroes()
        static("/images") {
            resources("images")
        }
    }
}