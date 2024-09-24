package com.borna.dotinfilm.sections.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film")
data class FilmEntity(
    @PrimaryKey
    val id: String,
    val name: String
)
