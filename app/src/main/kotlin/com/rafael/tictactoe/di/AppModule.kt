package com.rafael.tictactoe.di

import android.app.Application
import androidx.room.Room
import com.rafael.tictactoe.game.data.database.GameDatabase
import com.rafael.tictactoe.game.data.repository.GameRepositoryImpl
import com.rafael.tictactoe.game.domain.repository.GameRepository

import com.rafael.tictactoe.settings.data.database.SettingsDatabase
import com.rafael.tictactoe.settings.data.repository.SettingsRepositoryImpl
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository

import com.rafael.tictactoe.settings.domain.use_case.SettingsUseCases
import com.rafael.tictactoe.settings.domain.use_case.ToggleDarkTheme
import com.rafael.tictactoe.settings.domain.use_case.ToggleDynamicColors
import com.rafael.tictactoe.settings.domain.use_case.ToggleBackgroundMusic
import com.rafael.tictactoe.settings.domain.use_case.ToggleSoundEffects
import com.rafael.tictactoe.settings.domain.use_case.ToggleFollowingSystem
import com.rafael.tictactoe.settings.domain.use_case.ToggleAllSettingsValues

import com.rafael.tictactoe.settings.domain.util.SettingsUtilities
import com.rafael.tictactoe.settings.domain.util.GetSettingsValues
import com.rafael.tictactoe.settings.domain.util.HandleSwitchClicks
import com.rafael.tictactoe.settings.domain.util.HandleCardText
import com.rafael.tictactoe.settings.domain.util.HandleCardImage
import com.rafael.tictactoe.settings.domain.util.HandleSwitchValues
import dagger.Provides
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.rafael.tictactoe.game.domain.util.GameUtils
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.DisplayPlayerNames


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
fun provideGameUtilities(repository: GameRepository): GameUtils {
	return GameUtils(
    	coordinate = WinnerLineCoordinates(repository),
		playerName = DisplayPlayerNames(repository)
	)
}

// -> -> -> Bellow this line, all provides functions are related to settings part <- <- <-
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
fun provideSettingsUseCases(repository: SettingsRepository): SettingsUseCases {
	return SettingsUseCases(
		toggleDarkTheme = ToggleDarkTheme(repository),
		toggleDynamicColors = ToggleDynamicColors(repository),
		toggleBackgroundMusic = ToggleBackgroundMusic(repository),
		toggleSoundEffects = ToggleSoundEffects(repository),
		toggleFollowingSystem = ToggleFollowingSystem(repository),
        toggleAllSettingsValues = ToggleAllSettingsValues(repository)
    )
}


@Provides
@Singleton
fun provideSettingsUtilities(repository: SettingsRepository): SettingsUtilities {
	return SettingsUtilities(
		getSettingsValues = GetSettingsValues(repository)
       // handleCardImage = HandleCardImage(index, isDarkThemeOn, isDynamicColorsOn, isSoundEffectsOn, isBackgroundMusicOn)
      //  handleCardText = HandleCardText(repository),
       // handleSwitchClicks = HandleSwitchClicks(repository),
       // handleSwitchValues = HandleSwitchValues(repository)
    )
}


//TODO//Implement gameUseCases

}