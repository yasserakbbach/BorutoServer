package com.yasserakbbach.borutoserver.routes

import com.yasserakbbach.borutoserver.models.HeroResponse
import com.yasserakbbach.borutoserver.repository.HeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getAllHeroes() {
    val heroRepository: HeroRepository by inject()
    get("/boruto/heroes") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            require(page in 1..5)
            val response = heroRepository.getAllHeroes(page)
            call.respond(
                message = response,
                status = HttpStatusCode.OK,
            )
        } catch(e: NumberFormatException) {
            call.respond(
                message = getNumberFormatExceptionResponse(),
                status = HttpStatusCode.BadRequest,
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = getHeroesNotFoundResponse(),
                status = HttpStatusCode.NotFound,
            )
        }
    }
}

private fun getNumberFormatExceptionResponse(): HeroResponse =
    HeroResponse(
        success = false,
        message = "Only numbers are allowed for <page> parameter"
    )

private fun getHeroesNotFoundResponse(): HeroResponse =
    HeroResponse(
        success = false,
        message = "Heroes not Found."
    )