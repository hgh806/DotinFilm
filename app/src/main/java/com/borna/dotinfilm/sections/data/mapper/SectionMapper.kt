package com.borna.dotinfilm.sections.data.mapper

import com.borna.dotinfilm.sections.data.remote.response.section.FilmDetails
import com.borna.dotinfilm.sections.data.remote.response.section.SectionDto
import com.borna.dotinfilm.sections.domain.models.Film
import com.borna.dotinfilm.sections.domain.models.Section


fun SectionDto.toSection(): Section {
    return Section(
        id = id,
        name = name,
        films = list.map { it.toFilm() },
        cardType = cardType
    )
}

fun FilmDetails.toFilm(): Film {
    return Film(
        id = id ?: -1,
        name = nameFa ?: nameEn ?: name ?: "Unknown",
        description = description ?: "",
        imageUrl = imageUrl ?: "",
        disliked = userPostInfo?.disliked ?: false,
        liked = userPostInfo?.liked ?: false,
        favorite = userPostInfo?.favorite ?: false,
        bannerUrl = bannerUrl ?: ""
    )
}