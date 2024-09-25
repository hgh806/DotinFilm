package com.borna.dotinfilm.sections.data.remote.response.filmDetails

import kotlinx.serialization.Serializable

@Serializable
data class FilmDetailsResponse(
    val buyedTime: Int,
    val content: Content,
    val isBuyed: Boolean,
    val type: String
)