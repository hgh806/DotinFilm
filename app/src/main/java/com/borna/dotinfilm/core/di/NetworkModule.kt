package com.borna.dotinfilm.core.di

import com.borna.dotinfilm.BuildConfig
import com.borna.dotinfilm.common.AppConstants
import com.borna.dotinfilm.core.data.remote.adapter.NetworkCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJsonConvertor(): Json {
        return Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    @Provides
    @Singleton
    fun provideNetworkCallAdapterFactory(): CallAdapter.Factory {
        return NetworkCallAdapterFactory()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            client.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        json: Json,
        client: OkHttpClient,
        networkCallAdapterFactory: CallAdapter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(networkCallAdapterFactory)
            .client(client)
            .build()
    }
}