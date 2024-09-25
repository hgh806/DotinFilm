package com.borna.dotinfilm.core.di

import android.content.Context
import androidx.room.Room
import com.borna.dotinfilm.core.data.local.DotinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): DotinDatabase {
        return Room
            .databaseBuilder(context, DotinDatabase::class.java, "dotin.db")
            .fallbackToDestructiveMigration()
            .enableMultiInstanceInvalidation()
            .build()
    }
}