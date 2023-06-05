package com.rafael.tictactoe.settings.domain.repository

import com.rafael.tictactoe.settings.domain.model.SettingsEntity


interface SettingsRepository {

    suspend fun getSettingsEntity(): SettingsEntity 
    suspend fun getDarkThemeValue(): Boolean
    suspend fun getSoundEffectsValue(): Boolean
    suspend fun getDynamicColorsValue(): Boolean
    suspend fun getFollowingSystemValue(): Boolean
    suspend fun getBackgroundMusicValue(): Boolean

    suspend fun setSettingsEntity(settingsEntity: SettingsEntity)
    suspend fun setIsDarkThemeOnValue(isDarkThemeOn: Boolean)
    suspend fun setIsSoundEffectsOnValue(isSoundEffectsOn: Boolean)
    suspend fun setIsDynamicColorsOnValue(isDynamicColorsOn: Boolean)
    suspend fun setIsFollowingSystemOnValue(isFollowingSystemOn: Boolean)
    suspend fun setIsBackgroundMusicOnValue(isBackgroundMusicOn: Boolean)
}