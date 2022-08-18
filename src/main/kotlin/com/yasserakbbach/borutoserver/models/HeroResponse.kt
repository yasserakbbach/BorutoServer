package com.yasserakbbach.borutoserver.models

import kotlinx.serialization.Serializable

@Serializable
data class HeroResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val heroes: List<Hero> = emptyList(),
)
