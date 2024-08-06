package com.rafael.tictactoe.feature.game.di.modules

import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.feature.game.data.repository.GameRepositoryImpl
import com.rafael.tictactoe.feature.game.domain.repository.GameRepository
import com.rafael.tictactoe.feature.game.presentation.viewmodel.GameViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual val gameModule: Module = module {
    single<ExoPlayer> { ExoPlayer.Builder(androidContext()).build() }

    single<GameRepository> {
        GameRepositoryImpl(get<HttpClient>())
    }
    viewModel {
        GameViewModel(get<GameRepository>())
    }
    single {
        HttpClient(Android.create()) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    },
                )
            }
        }
    }
}
