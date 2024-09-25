package com.borna.dotinfilm.sections.data.remote.response.filmDetails

import kotlinx.serialization.Serializable

@Serializable
data class UserPostInfo(
    val disliked: Boolean,
    val favorite: Boolean,
    val liked: Boolean
)