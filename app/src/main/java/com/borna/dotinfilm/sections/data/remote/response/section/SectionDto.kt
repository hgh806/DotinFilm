package com.borna.dotinfilm.sections.data.remote.response.section

import kotlinx.serialization.Serializable

@Serializable
data class SectionDto(
    val cardType: String,
    val id: Int,
    val list: List<FilmDetails>,
    val listType: String,
    val name: String
)