package com.rafael.tictactoe.settings.data.repository

import com.rafael.tictactoe.settings.domain.model.SettingsEntity
import com.rafael.tictactoe.settings.data.dao.SettingsDao
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import javax.inject.Inject


class SettingsRepositoryImpl @Inject constructor(
private val settingsDao: SettingsDao
) : SettingsRepository {
    override suspend fun getSettingsEntity(): SettingsEntity {
        return settingsDao.getSettingsEntity()
    }
   override suspend fun setSettingsEntity(settingsEntity: SettingsEntity) {
        return settingsDao.setSettingsEntity(settingsEntity)
    }
    override suspend fun getDarkThemeValue(): Boolean {
        return settingsDao.getDarkThemeValue()
    }
    override suspend fun getSoundEffectsValue(): Boolean {
        return settingsDao.getSoundEffectsValue()
    }
    override suspend fun getDynamicColorsValue(): Boolean {
        return settingsDao.getDynamicColorsValue()
    }
    override suspend fun getFollowingSystemValue(): Boolean {
        return settingsDao.getFollowingSystemValue()
    }
    override suspend fun getBackgroundMusicValue(): Boolean {
        return settingsDao.getBackgroundMusicValue()
    }

    override suspend fun setIsDarkThemeOnValue(isDarkThemeOn: Boolean) {
        return settingsDao.setIsDarkThemeOnValue(isDarkThemeOn)
    }
    override suspend fun setIsSoundEffectsOnValue(isSoundEffectsOn: Boolean) {
        return settingsDao.setIsSoundEffectsOnValue(isSoundEffectsOn)
    }
    override suspend fun setIsDynamicColorsOnValue(isDynamicColorsOn: Boolean) {
        return settingsDao.setIsDynamicColorsOnValue(isDynamicColorsOn)
    }
    override suspend fun setIsFollowingSystemOnValue(isFollowingSystemOn: Boolean) {
        return settingsDao.setIsFollowingSystemOnValue(isFollowingSystemOn)
    }
    override suspend fun setIsBackgroundMusicOnValue(isBackgroundMusicOn: Boolean) {
        return settingsDao.setIsBackgroundMusicOnValue(isBackgroundMusicOn)
    } 
}