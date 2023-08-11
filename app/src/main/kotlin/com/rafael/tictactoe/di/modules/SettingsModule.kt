package com.rafael.tictactoe.di

import android.app.Application
import androidx.room.Room
import com.rafael.tictactoe.settings.data.database.SettingsDatabase
import com.rafael.tictactoe.settings.data.repository.SettingsRepositoryImpl
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.domain.util.CardTextHandler
import com.rafael.tictactoe.settings.domain.util.CardImageHandler
import com.rafael.tictactoe.settings.domain.util.SwitchOnCheckedChangeHandler
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

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