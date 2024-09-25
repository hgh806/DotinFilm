package com.borna.dotinfilm.sections.data.remote.response.filmDetails

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val code: String,
    val name: String
)