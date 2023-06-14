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
}