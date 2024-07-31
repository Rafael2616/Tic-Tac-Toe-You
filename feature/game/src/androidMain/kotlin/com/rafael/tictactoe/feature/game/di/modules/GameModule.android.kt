package com.rafael.tictactoe.feature.game.di.modules

import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.feature.game.data.dao.OnlineGameDao
import com.rafael.tictactoe.feature.game.data.repository.AndroidGameRepositoryImpl
import com.rafael.tictactoe.feature.game.domain.repository.GameRepository
import com.rafael.tictactoe.feature.game.presentation.viewmodel.GameViewModel
import com.rafael.tictactoe.feature.settings.presentation.viewmodel.SettingsViewModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

actual val gameModule: Module = module {
    single<ExoPlayer> { ExoPlayer.Builder(androidContext()).build() }

    single<GameRepository> {
        AndroidGameRepositoryImpl(get<OnlineGameDao>())
    }
    viewModel {
        GameViewModel(
            get<SettingsViewModel>(),
            get<GameRepository>(),
        )
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://retoolapi.dev/IdekO9/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(
                Json { ignoreUnknownKeys = true }
                    .asConverterFactory("application/json".toMediaType()),
            )
            .build()
    }
    single<OnlineGameDao> { get<Retrofit>().create(OnlineGameDao::class.java) }
}
