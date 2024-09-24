package com.borna.dotinfilm.sections.data.repository

import com.borna.dotinfilm.core.data.remote.adapter.DotinApiErr
import com.borna.dotinfilm.core.data.remote.adapter.NetworkResponse
import com.borna.dotinfilm.sections.data.remote.api.FilmApiService
import com.borna.dotinfilm.sections.data.remote.response.section.SectionResponseDto
import com.borna.dotinfilm.sections.domain.repository.FilmRepository
import javax.inject.Inject

class FilmRepositoryImp @Inject constructor(
    private val api: FilmApiService
) : FilmRepository {

    override suspend fun getSections(): NetworkResponse<SectionResponseDto, DotinApiErr> {
        return api.getSections()
    }
}