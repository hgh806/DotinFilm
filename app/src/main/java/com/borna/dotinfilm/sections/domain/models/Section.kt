package com.borna.dotinfilm.sections.domain.models

data class Section(
    val id: Int,
    val name: String,
    val films: List<Film>,
    val cardType: String
)
