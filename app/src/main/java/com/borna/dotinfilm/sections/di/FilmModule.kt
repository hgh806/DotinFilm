package com.borna.dotinfilm.sections.di

import com.borna.dotinfilm.sections.data.remote.api.FilmApiService
import com.borna.dotinfilm.sections.data.repository.FilmRepositoryImp
import com.borna.dotinfilm.sections.domain.repository.FilmRepository
import com.borna.dotinfilm.sections.domain.use_cases.GetSectionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FilmModule {
    @Provides
    @Singleton
    fun provideFilmApiService(
        retrofit: Retrofit
    ): FilmApiService {
        return retrofit.create(FilmApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideFilmRepository(filmApiService: FilmApiService): FilmRepository {
        return FilmRepositoryImp(filmApiService)
    }

    @Provides
    @Singleton
    fun provideGetSectionsUseCase(filmRepository: FilmRepository): GetSectionsUseCase {
        return GetSectionsUseCase(filmRepository)
    }
}