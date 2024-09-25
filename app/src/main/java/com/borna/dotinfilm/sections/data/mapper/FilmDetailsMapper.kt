package com.borna.dotinfilm.sections.data.mapper

import com.borna.dotinfilm.sections.data.remote.response.filmDetails.FilmDetailsResponse
import com.borna.dotinfilm.sections.domain.models.FilmDetails


fun FilmDetailsResponse.toFilmDetails(): FilmDetails {
    return FilmDetails(
        id = content.id ?: -1,
        name = content.nameFa ?: content.nameEn ?: "Unknown",
        description = content.description ?: "",
        images = content.galleries ?: emptyList(),
        liked = content.userPostInfo?.liked ?: false,
        bannerUrl = content.bannerUrl ?: "",
        age = content.ages ?: 0,
        genres = content.genres?.map { it.name } ?: emptyList(),
        imdb = content.imdbRate.toString(),
        duration = content.duration ?: 0
    )
}