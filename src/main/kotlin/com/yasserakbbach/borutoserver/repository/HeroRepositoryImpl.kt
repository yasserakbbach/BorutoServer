package com.yasserakbbach.borutoserver.repository

import com.yasserakbbach.borutoserver.models.Hero
import com.yasserakbbach.borutoserver.models.HeroResponse

class HeroRepositoryImpl : HeroRepository {

    override val heroes: Map<Int, List<Hero>>
        get() = TODO("Not yet implemented")
    override val page1: List<Hero>
        get() = TODO("Not yet implemented")
    override val page2: List<Hero>
        get() = TODO("Not yet implemented")
    override val page3: List<Hero>
        get() = TODO("Not yet implemented")
    override val page4: List<Hero>
        get() = TODO("Not yet implemented")
    override val page5: List<Hero>
        get() = TODO("Not yet implemented")

    override suspend fun getAllHeroes(page: Int): HeroResponse {
        TODO("Not yet implemented")
    }

    override suspend fun searchHeroes(name: String?): HeroResponse {
        TODO("Not yet implemented")
    }
}