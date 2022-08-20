package com.yasserakbbach.borutoserver.routes

import com.yasserakbbach.borutoserver.models.HeroResponse
import com.yasserakbbach.borutoserver.repository.HeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.searchHeroes() {
    val heroRepository: HeroRepository by inject()
    get("/boruto/heroes/search") {
        try {
            val name = call.request.queryParameters["name"]
            requireNotNull(name)
            val response = heroRepository.searchHeroes(name)
            call.respond(
                message = response,
                status = HttpStatusCode.OK,
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = getNullNameParameterExceptionResponse(),
                status = HttpStatusCode.BadRequest,
            )
        }
    }
}

private fun getNullNameParameterExceptionResponse(): HeroResponse =
    HeroResponse(
        success = false,
        message = "Parameter <name> is required",
    )