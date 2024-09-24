package com.borna.dotinfilm.sections.domain.models

data class Film(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val bannerUrl: String,
    val disliked: Boolean,
    val favorite: Boolean,
    val liked: Boolean
)
