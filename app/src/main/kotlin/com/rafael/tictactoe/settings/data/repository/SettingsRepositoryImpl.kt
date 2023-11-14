package com.rafael.tictactoe.settings.data.repository

import com.rafael.tictactoe.settings.domain.model.SettingsEntity
import com.rafael.tictactoe.settings.data.dao.SettingsDao
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val settingsDao: SettingsDao
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