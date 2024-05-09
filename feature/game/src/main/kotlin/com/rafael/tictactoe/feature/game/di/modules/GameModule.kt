package com.rafael.tictactoe.feature.game.di.modules

import android.app.Application
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.feature.game.data.dao.OnlineGameDao
import com.rafael.tictactoe.feature.game.data.repository.GameRepositoryImpl
import com.rafael.tictactoe.feature.game.domain.repository.GameRepository
import com.rafael.tictactoe.feature.game.presentation.viewmodel.GameViewModel
import com.rafael.tictactoe.feature.settings.presentation.viewmodel.SettingsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

class GameModule {

    @Module
    @InstallIn(SingletonComponent::class)
    object GameDataModule {

        @Provides
        @Singleton
        fun provideGameRepository(
            retrofitDao: OnlineGameDao,
        ): GameRepository {
            return GameRepositoryImpl(
                retrofitDao,
            )
        }
    }

    @Module
    @InstallIn(ViewModelComponent::class)
    object GameViewModelModule {

        @Provides
        @ViewModelScoped
        fun provideGameViewModel(
            player: ExoPlayer,
            settingsVM: SettingsViewModel,
            context: Application,
            repository: GameRepository,
        ): GameViewModel {
            return GameViewModel(
                player,
                settingsVM,
                context,
                repository,
            )
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object GameRetrofitServiceModule {

        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://retoolapi.dev/IdekO9/")
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(
                    Json { ignoreUnknownKeys = true }
                        .asConverterFactory("application/json".toMediaType()),
                )
                .build()
        }

        @Provides
        fun provideGameService(retrofit: Retrofit): OnlineGameDao {
            return retrofit.create(OnlineGameDao::class.java)
        }
    }
}
