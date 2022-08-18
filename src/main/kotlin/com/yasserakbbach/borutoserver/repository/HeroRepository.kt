package com.yasserakbbach.borutoserver.repository

import com.yasserakbbach.borutoserver.models.Hero
import com.yasserakbbach.borutoserver.models.HeroResponse

interface HeroRepository {
    val heroes: Map<Int, List<Hero>>

    val page1: List<Hero>
    val page2: List<Hero>
    val page3: List<Hero>
    val page4: List<Hero>
    val page5: List<Hero>

    suspend fun getAllHeroes(page: Int = 1): HeroResponse
    suspend fun searchHeroes(name: String?): HeroResponse
}