package com.borna.dotinfilm.sections.presentation.detail

import com.borna.dotinfilm.sections.domain.models.Section

data class FilmUiState(
    val isLoading: Boolean = false,
    val sections: List<Section> = emptyList(),
    val errorMessage: String? = null,
    val errorMessageId: Int? = null
)
