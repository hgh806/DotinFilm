package com.borna.dotinfilm.sections.data.remote.response.section

import kotlinx.serialization.Serializable

@Serializable
data class SectionResponseDto(
    val sections: List<SectionDto>
)
