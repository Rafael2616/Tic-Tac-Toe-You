package com.rafael.tictactoe.di

import android.app.Application
import androidx.room.Room
import com.rafael.tictactoe.game.data.database.GameDatabase
import com.rafael.tictactoe.game.data.repository.GameRepositoryImpl
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.util.GameUtils
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.PlayersNamesDisplay
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
    fun provideGameRepository(database: GameDatabase): GameRepository {
        return GameRepositoryImpl(database.gameDao)
    }

    //TODO// add misssing gameUtils here
    @Provides
    @Singleton
    fun provideGameUtilities(): GameUtils {
        return GameUtils(
            coordinate = WinnerLineCoordinates(),
            playerName = PlayersNamesDisplay()
        )
    }
}