package com.borna.dotinfilm.sections.domain.models


data class FilmDetails(
    val id: Int,
    val name: String,
    val description: String,
    val age: Int,
    val duration: Int,
    val bannerUrl: String,
    val liked: Boolean,
    val imdb: String,
    val genres: List<String>,
    val images: List<String>,
)
