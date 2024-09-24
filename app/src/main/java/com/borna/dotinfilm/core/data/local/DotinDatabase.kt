package com.borna.dotinfilm.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.borna.dotinfilm.sections.data.local.dao.FilmDao
import com.borna.dotinfilm.sections.data.local.entities.FilmEntity
import com.borna.dotinfilm.sections.domain.models.Film

@Database(
    version = 1,
    exportSchema = true,
    entities = [
        FilmEntity::class
    ]
)
abstract class DotinDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}