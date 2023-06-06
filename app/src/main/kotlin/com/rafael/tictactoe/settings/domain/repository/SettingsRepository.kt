package com.rafael.tictactoe.settings.domain.repository

import com.rafael.tictactoe.settings.domain.model.SettingsEntity


interface SettingsRepository {

    suspend fun getAllSettingsValues(): SettingsEntity
    suspend fun setAllSettingsValues(settingsEntity: SettingsEntity)

    suspend fun getIsDarkThemeOn(): Boolean
    suspend fun setIsDarkThemeOn(isDarkThemeOn: Boolean)

    suspend fun getIsSoundEffectsOn(): Boolean
    suspend fun setIsSoundEffectsOn(isSoundEffectsOn: Boolean)

    suspend fun getIsDynamicColorsOn(): Boolean
    suspend fun setIsDynamicColorsOn(isDynamicColorsOn: Boolean)

    suspend fun getIsFollowingSystemOn(): Boolean
    suspend fun setIsFollowingSystemOn(isFollowingSystemOn: Boolean)

    suspend fun getIsBackgroundMusicOn(): Boolean
    suspend fun setIsBackgroundMusicOn(isBackgroundMusicOn: Boolean)
}