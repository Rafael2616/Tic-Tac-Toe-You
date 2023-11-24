package com.rafael.tictactoe.di

import android.app.Application
import androidx.media3.exoplayer.ExoPlayer
import androidx.room.Room
import com.rafael.tictactoe.game.data.dao.OnlineGameDao
import com.rafael.tictactoe.game.data.database.GameDatabase
import com.rafael.tictactoe.game.data.repository.GameRepositoryImpl
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GameModule {

    @Provides
    @Singleton
    fun provideGameDatabase(app: Application): GameDatabase {
        return Room.databaseBuilder(
            app,
            GameDatabase::class.java,
            GameDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideGameRepository(
        database: GameDatabase,
        dao: OnlineGameDao
    ): GameRepository {
        return GameRepositoryImpl(
            database.gameDao(),
            dao
        )
    }

    @Provides
    @Singleton
    fun provideGameViewModel(
        player: ExoPlayer,
        settingsVM: SettingsViewModel,
        context: Application,
        repository: GameRepository
    ): GameViewModel {
        return GameViewModel(
            player,
            settingsVM,
            context,
            repository
        )
    }
}