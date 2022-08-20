package com.yasserakbbach.borutoserver.repository

import com.yasserakbbach.borutoserver.models.Hero
import com.yasserakbbach.borutoserver.models.HeroResponse
import com.yasserakbbach.borutoserver.util.HeroSample.heroPage1
import com.yasserakbbach.borutoserver.util.HeroSample.heroPage2
import com.yasserakbbach.borutoserver.util.HeroSample.heroPage3
import com.yasserakbbach.borutoserver.util.HeroSample.heroPage4
import com.yasserakbbach.borutoserver.util.HeroSample.heroPage5

class HeroRepositoryImpl : HeroRepository {

    override val heroes: Map<Int, List<Hero>> by lazy {
        mapOf(
            1 to page1,
            2 to page2,
            3 to page3,
            4 to page4,
            5 to page5,
        )
    }

    override val page1 = heroPage1()
    override val page2 = heroPage2()
    override val page3 = heroPage3()
    override val page4 = heroPage4()
    override val page5 = heroPage5()

    override suspend fun getAllHeroes(page: Int): HeroResponse =
        HeroResponse(
            success = true,
            message = "list of heroes",
            prevPage = page.calculatePreviousPage(),
            nextPage = page.calculateNextPage(),
            heroes = requireNotNull(heroes[page]),
        )

    private fun Int.calculateNextPage(): Int? =
        when(this) {
            in 1..4 -> plus(1)
            else -> null
        }

    private fun Int.calculatePreviousPage(): Int? =
        when(this) {
            in 2..heroes.size -> minus(1)
            else -> null
        }

    override suspend fun searchHeroes(name: String?): HeroResponse =
        HeroResponse(
            success = true,
            message = name?.let { "Results for $name" },
            heroes = findHeroesBy(name),
        )

    private fun findHeroesBy(name: String?): List<Hero> =
        name?.run {
            heroes.values.flatten().filter {
                it.name.lowercase().contains(name.lowercase())
            }
        } ?: emptyList()
}