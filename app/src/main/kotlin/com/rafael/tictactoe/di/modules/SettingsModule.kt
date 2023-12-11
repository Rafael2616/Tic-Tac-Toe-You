package com.rafael.tictactoe.di

import android.app.Application
import androidx.media3.exoplayer.ExoPlayer
import androidx.room.Room
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.settings.data.database.Migrations
import com.rafael.tictactoe.settings.data.database.SettingsDatabase
import com.rafael.tictactoe.settings.data.repository.SettingsRepositoryImpl
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.presentation.settingsScreen.viewmodels.SettingsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun provideSettingsDatabase(context: Application): SettingsDatabase {
        return Room.databaseBuilder(
            context,
            SettingsDatabase::class.java,
            SettingsDatabase.DATABASE_NAME,
        ).addMigrations(Migrations().migration1To2)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(database: SettingsDatabase): SettingsRepository {
        return SettingsRepositoryImpl(database.settingsDao())
    }

    @Provides
    @Singleton
    fun provideSettingsViewModel(
        repository: SettingsRepository,
        player: ExoPlayer,
        context: Application,
        service: MusicService,
    ): SettingsViewModel {
        return SettingsViewModel(
            repository,
            player,
            context,
            service,
        )
    }
}
