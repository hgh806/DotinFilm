package com.borna.dotinfilm.sections.presentation.film

import com.borna.dotinfilm.sections.domain.models.Section

data class HomeUiState(
    val isLoading: Boolean = false,
    val sections: List<Section> = emptyList(),
    val errorMessage: String? = null,
    val errorMessageId: Int? = null
)
