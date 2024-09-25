package com.borna.dotinfilm.sections.domain.repository

import com.borna.dotinfilm.core.data.remote.adapter.DotinApiErr
import com.borna.dotinfilm.core.data.remote.adapter.NetworkResponse
import com.borna.dotinfilm.sections.data.remote.response.filmDetails.FilmDetailsResponse
import com.borna.dotinfilm.sections.data.remote.response.section.SectionResponseDto

interface FilmRepository {
    suspend fun getSections(): NetworkResponse<SectionResponseDto, DotinApiErr>
    suspend fun getFilmDetails(filmId: Int): NetworkResponse<FilmDetailsResponse, DotinApiErr>
}