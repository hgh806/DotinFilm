package com.borna.dotinfilm.sections.data.remote.response.section

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val code: String,
    val name: String
)