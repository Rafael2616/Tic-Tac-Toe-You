package com.rafael.tictactoe.feature.game.di.modules

import com.rafael.tictactoe.feature.game.data.repository.GameRepositoryImpl
import com.rafael.tictactoe.feature.game.domain.repository.GameRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

actual val gameModule: Module = module {
    single<GameRepository> {
        GameRepositoryImpl(get<HttpClient>())
    }
    single {
        HttpClient(Darwin.create()) {
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
