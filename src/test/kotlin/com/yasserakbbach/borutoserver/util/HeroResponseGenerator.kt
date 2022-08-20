package com.yasserakbbach.borutoserver.util

import com.yasserakbbach.borutoserver.models.HeroResponse

object HeroResponseGenerator {

    fun generateNumberFormatExceptionResponse(): HeroResponse =
        HeroResponse(
            success = false,
            message = "Only numbers are allowed for <page> parameter"
        )

    fun generateHeroesNotFoundResponse(): HeroResponse =
        HeroResponse(
            success = false,
            message = "Heroes not Found."
        )
}