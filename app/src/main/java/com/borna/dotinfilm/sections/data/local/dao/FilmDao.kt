package com.borna.dotinfilm.sections.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.borna.dotinfilm.sections.data.local.entities.FilmEntity

@Dao
interface FilmDao {
    @Insert
    suspend fun insertFilms(films: List<FilmEntity>)
}