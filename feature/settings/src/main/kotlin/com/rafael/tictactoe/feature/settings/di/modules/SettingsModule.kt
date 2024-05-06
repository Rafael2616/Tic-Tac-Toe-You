package com.rafael.tictactoe.di

import android.app.Application
import androidx.media3.exoplayer.ExoPlayer
import androidx.room.Room
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.settings.settingsScreen.data.database.Migrations
import com.rafael.tictactoe.settings.settingsScreen.data.database.SettingsDatabase
import com.rafael.tictactoe.settings.settingsScreen.data.repository.SettingsRepositoryImpl
import com.rafael.tictactoe.settings.settingsScreen.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodel.SettingsViewModel
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
        ).addMigrations(Migrations().migration1To2, Migrations().migration2To3)
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
