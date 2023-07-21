package com.rafael.tictactoe.di

import android.app.Application
import androidx.room.Room
import com.rafael.tictactoe.game.data.database.GameDatabase
import com.rafael.tictactoe.game.data.repository.GameRepositoryImpl
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.util.GameUtils
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.PlayersNamesDisplay
import com.rafael.tictactoe.settings.data.database.SettingsDatabase
import com.rafael.tictactoe.settings.data.repository.SettingsRepositoryImpl
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.domain.util.HandleSwitchClicks
import com.rafael.tictactoe.settings.domain.util.HandleCardText
import com.rafael.tictactoe.settings.domain.util.HandleCardImage
import com.rafael.tictactoe.settings.domain.util.HandleSwitchValues
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.hilt.components.SingletonComponent
import dagger.hilt.InstallIn
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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

    // -> -> -> Bellow this line, all provides functions are related to settings feature <- <- <-
    @Provides
    @Singleton
    fun provideSettingsDatabase(app: Application): SettingsDatabase {
        return Room.databaseBuilder(
            app,
            SettingsDatabase::class.java,
            SettingsDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(database: SettingsDatabase): SettingsRepository {
        return SettingsRepositoryImpl(database.settingsDao)
    }
	
    @Provides
    @Singleton
    fun provideSettingsViewModel(repository: SettingsRepository): SettingsViewModel {
        return SettingsViewModel(repository)
    }

    //TODO// add misssing settingsUtils here
    /*@Provides
    @Singleton
    fun provideSettingsUtilities(repository: SettingsRepository): SettingsUtilities {
        return SettingsUtilities(
            handleCardImage = HandleCardImage(index, isDarkThemeOn, isDynamicColorsOn, isSoundEffectsOn, isBackgroundMusicOn)
            handleCardText = HandleCardText(repository),
            handleSwitchClicks = HandleSwitchClicks(repository),
            handleSwitchValues = HandleSwitchValues(repository)
        )
    }*/
}