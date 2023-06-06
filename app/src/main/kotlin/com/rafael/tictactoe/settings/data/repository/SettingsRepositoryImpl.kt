package com.rafael.tictactoe.settings.data.repository

import com.rafael.tictactoe.settings.domain.model.SettingsEntity
import com.rafael.tictactoe.settings.data.dao.SettingsDao
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import javax.inject.Inject


class SettingsRepositoryImpl @Inject constructor(
private val settingsDao: SettingsDao
) : SettingsRepository {
    override suspend fun getAllSettingsValues(): SettingsEntity {
        return settingsDao.getAllSettingsValues()
    }
    override suspend fun setAllSettingsValues(settingsEntity: SettingsEntity) {
        return settingsDao.setAllSettingsValues(settingsEntity)
    }

    override suspend fun getIsDarkThemeOn(): Boolean {
        return settingsDao.getIsDarkThemeOn()
    }
    override suspend fun setIsDarkThemeOn(isDarkThemeOn: Boolean) {
        return settingsDao.setIsDarkThemeOn(isDarkThemeOn)
    }

    override suspend fun getIsSoundEffectsOn(): Boolean {
        return settingsDao.getIsSoundEffectsOn()
    }
    override suspend fun setIsSoundEffectsOn(isSoundEffectsOn: Boolean) {
        return settingsDao.setIsSoundEffectsOn(isSoundEffectsOn)
    }

    override suspend fun getIsDynamicColorsOn(): Boolean {
        return settingsDao.getIsDynamicColorsOn()
    }
    override suspend fun setIsDynamicColorsOn(isDynamicColorsOn: Boolean) {
        return settingsDao.setIsDynamicColorsOn(isDynamicColorsOn)
    }

    override suspend fun getIsFollowingSystemOn(): Boolean {
        return settingsDao.getIsFollowingSystemOn()
    }
    override suspend fun setIsFollowingSystemOn(isFollowingSystemOn: Boolean) {
        return settingsDao.setIsFollowingSystemOn(isFollowingSystemOn)
    }

    override suspend fun getIsBackgroundMusicOn(): Boolean {
        return settingsDao.getIsBackgroundMusicOn()
    }
    override suspend fun setIsBackgroundMusicOn(isBackgroundMusicOn: Boolean) {
        return settingsDao.setIsBackgroundMusicOn(isBackgroundMusicOn)
    }

}