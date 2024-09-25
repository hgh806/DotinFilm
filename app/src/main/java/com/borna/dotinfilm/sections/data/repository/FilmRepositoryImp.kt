package com.borna.dotinfilm.sections.data.repository

import com.borna.dotinfilm.core.data.remote.adapter.DotinApiErr
import com.borna.dotinfilm.core.data.remote.adapter.NetworkResponse
import com.borna.dotinfilm.sections.data.local.dao.FilmDao
import com.borna.dotinfilm.sections.data.local.entities.FilmEntity
import com.borna.dotinfilm.sections.data.remote.api.FilmApiService
import com.borna.dotinfilm.sections.data.remote.response.filmDetails.FilmDetailsResponse
import com.borna.dotinfilm.sections.data.remote.response.section.SectionResponseDto
import com.borna.dotinfilm.sections.domain.repository.FilmRepository
import javax.inject.Inject

class FilmRepositoryImp @Inject constructor(
    private val api: FilmApiService,
    private val filmDao: FilmDao
) : FilmRepository {

    override suspend fun getSections(): NetworkResponse<SectionResponseDto, DotinApiErr> {
        return api.getSections()
    }

    override suspend fun getFilmDetails(filmId: Int): NetworkResponse<FilmDetailsResponse, DotinApiErr> {
        return api.getFilmDetails(filmId)
    }

    override suspend fun saveFilm(filmEntity: FilmEntity) {
        filmDao.insertFilm(filmEntity)
    }
}