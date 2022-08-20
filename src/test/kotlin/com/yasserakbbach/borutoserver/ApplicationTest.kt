package com.yasserakbbach.borutoserver

import com.yasserakbbach.borutoserver.models.HeroResponse
import com.yasserakbbach.borutoserver.util.HeroResponseGenerator.generateHeroesNotFoundResponse
import com.yasserakbbach.borutoserver.util.HeroResponseGenerator.generateNumberFormatExceptionResponse
import com.yasserakbbach.borutoserver.util.HeroSample
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.test.dispatcher.*
import io.ktor.util.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@InternalAPI
class ApplicationTest {
    @Test
    fun `access root endpoint, assert correct information`() = testApplication {
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("\"Welcome to Boruto API\"", response.bodyAsText())
    }

    @Test
    fun `given first page of heroes When calling API Then expected equals actual`() = testApplication {
        val response = client.get("/boruto/heroes")
        val page = 1
        assertEquals(HttpStatusCode.OK, response.status)
        val expected = HeroResponse(
            success = true,
            message = "list of heroes",
            prevPage = page.calculatePreviousPage(5),
            nextPage = page.calculateNextPage(),
            heroes = HeroSample.heroPage1(),
        )
        val actual = Json.decodeFromString<HeroResponse>(response.bodyAsText())
        assertEquals(expected, actual)
    }

    @Test
    fun `given second page of heroes When calling API Then expected equals actual`() = testApplication {
        val response = client.get("/boruto/heroes?page=2")
        val page = 2
        assertEquals(HttpStatusCode.OK, response.status)
        val expected = HeroResponse(
            success = true,
            message = "list of heroes",
            prevPage = page.calculatePreviousPage(5),
            nextPage = page.calculateNextPage(),
            heroes = HeroSample.heroPage2(),
        )
        val actual = Json.decodeFromString<HeroResponse>(response.bodyAsText())
        assertEquals(expected, actual)
    }

    @Test
    fun `given all heroes pages When calling API Then expected equals actual`() = testApplication {
        val pagesHeroes = mapOf(
            1 to HeroSample.heroPage1(),
            2 to HeroSample.heroPage2(),
            3 to HeroSample.heroPage3(),
            4 to HeroSample.heroPage4(),
            5 to HeroSample.heroPage5(),
        )
        pagesHeroes.forEach { (page, heroes) ->
            testSuspend {
                val response = client.get("/boruto/heroes?page=$page")
                assertEquals(HttpStatusCode.OK, response.status)
                val expected = HeroResponse(
                    success = true,
                    message = "list of heroes",
                    prevPage = page calculatePreviousPage pagesHeroes.size,
                    nextPage = page.calculateNextPage(),
                    heroes = heroes,
                )
                val actual = Json.decodeFromString<HeroResponse>(response.bodyAsText())
                assertEquals(expected, actual)
            }
        }
    }
    @Test
    fun `given non-existing page number When calling API Then error is expected`() = testApplication {
        val response = client.get("/boruto/heroes?page=6")
        assertEquals(HttpStatusCode.NotFound, response.status)
        val expected = generateHeroesNotFoundResponse()
        val actual = Json.decodeFromString<HeroResponse>(response.bodyAsText())
        assertEquals(expected, actual)
    }

    @Test
    fun `given non-number page format When calling API Then error is expected`() = testApplication {
        val response = client.get("/boruto/heroes?page=yasser")
        assertEquals(HttpStatusCode.BadRequest, response.status)
        val expected = generateNumberFormatExceptionResponse()
        val actual = Json.decodeFromString<HeroResponse>(response.bodyAsText())
        assertEquals(expected, actual)
    }

    private fun Int.calculateNextPage(): Int? =
        when(this) {
            in 1..4 -> plus(1)
            else -> null
        }

    private infix fun Int.calculatePreviousPage(size: Int): Int? =
        when(this) {
            in 2..size -> minus(1)
            else -> null
        }
}