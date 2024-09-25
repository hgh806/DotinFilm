package com.borna.dotinfilm.sections.data.remote.response.filmDetails

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val code: String,
    val name: String
)