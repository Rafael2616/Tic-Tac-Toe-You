package com.rafael.tictactoe.core.services.di.modules

import android.app.Application
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.core.services.music.MusicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object ServicesModule {

    @Provides
    internal fun provideExoPlayerInstance(context: Application): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }

    @Provides
    internal fun provideMusicService(): MusicService {
        return MusicService()
    }
}
