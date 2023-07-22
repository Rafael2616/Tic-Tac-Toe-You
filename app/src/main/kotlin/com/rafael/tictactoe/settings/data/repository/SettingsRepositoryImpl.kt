package com.rafael.tictactoe.settings.data.repository

import com.rafael.tictactoe.settings.domain.model.SettingsEntity
import com.rafael.tictactoe.settings.data.dao.SettingsDao
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl @Inject constructor(
private val settingsDao: SettingsDao
) : SettingsRepository {

	init{
		println("Settings repository has been initialized")
	}

    override fun getAllSettingsValues(): Flow<SettingsEntity> {
        return settingsDao.getSettingsValues()
    }

    override suspend fun setAllSettingsValues(settingsEntity: SettingsEntity) {
        return settingsDao.setSettingsValues(settingsEntity)
    }
}