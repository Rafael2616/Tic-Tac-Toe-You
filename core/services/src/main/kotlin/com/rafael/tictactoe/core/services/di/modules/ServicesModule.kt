package com.rafael.tictactoe.core.services.di.modules

import android.app.Application
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.core.services.MusicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    fun provideExoPlayerInstance(context: Application): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }

    @Provides
    fun provideMusicService(): MusicService {
        return MusicService()
    }
}
