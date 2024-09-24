package com.borna.dotinfilm.sections.data.remote.api

import com.borna.dotinfilm.core.data.remote.adapter.DotinApiErr
import com.borna.dotinfilm.core.data.remote.adapter.NetworkResponse
import com.borna.dotinfilm.sections.data.remote.response.section.FilmDetails
import com.borna.dotinfilm.sections.data.remote.response.section.SectionResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmApiService {
    @GET("config/pages/tabs/5850835/sections")
    suspend fun getSections(): NetworkResponse<SectionResponseDto, DotinApiErr>

    @GET("contents/{filmId}")
    suspend fun getFilmDetails(@Path("filmId") filmId: Int): NetworkResponse<FilmDetails, DotinApiErr>
}