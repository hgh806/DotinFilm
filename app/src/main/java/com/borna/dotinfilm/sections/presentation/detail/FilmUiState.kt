package com.borna.dotinfilm.sections.presentation.detail

import com.borna.dotinfilm.sections.domain.models.FilmDetails

data class FilmUiState(
    val isLoading: Boolean = false,
    val filmDetails: FilmDetails? = null,
    val errorMessage: String? = null,
    val errorMessageId: Int? = null
)
