package com.rafael.tictactoe.core.services.di.modules

import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.core.services.music.MusicService
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val servicesModule: Module = module {
    single {
        ExoPlayer.Builder(androidApplication()).build()
    }

    single {
        MusicService()
    }
}
