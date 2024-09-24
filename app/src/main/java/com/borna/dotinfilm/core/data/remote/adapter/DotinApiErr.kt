package com.borna.dotinfilm.core.data.remote.adapter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DotinApiErr(
    @SerialName("message")
    val message: String? = null,
    @SerialName("description")
    val description: String? = null,
)