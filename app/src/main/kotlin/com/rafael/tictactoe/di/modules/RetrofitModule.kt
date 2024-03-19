package com.rafael.tictactoe.di

import com.rafael.tictactoe.game.data.dao.OnlineGameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://retoolapi.dev/IdekO9/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    fun provideGameService(retrofit: Retrofit): OnlineGameDao {
        return retrofit.create(OnlineGameDao::class.java)
    }
}
