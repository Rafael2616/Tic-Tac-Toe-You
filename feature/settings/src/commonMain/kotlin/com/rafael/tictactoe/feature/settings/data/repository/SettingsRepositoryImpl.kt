package com.rafael.tictactoe.feature.settings.data.repository

import com.rafael.tictactoe.feature.settings.data.dao.SettingsDao
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEntity
import com.rafael.tictactoe.feature.settings.domain.repository.SettingsRepository

internal class SettingsRepositoryImpl(
    private val settingsDao: SettingsDao,
) : SettingsRepository {

    init {
        println("Settings repository has been initialized")
    }

    override suspend fun getAllSettingsValues(id: Int): SettingsEntity? {
        return settingsDao.getSettingsValues(id)
    }

    override suspend fun setAllSettingsValues(settingsEntity: SettingsEntity) {
        settingsDao.setAllSettingsValues(settingsEntity)
    }
}
