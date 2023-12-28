package com.rafael.tictactoe.settings.domain.repository

import com.rafael.tictactoe.settings.domain.model.SettingsEntity

interface SettingsRepository {

    suspend fun getAllSettingsValues(id: Int): SettingsEntity?
	
    suspend fun setAllSettingsValues(settingsEntity: SettingsEntity)
}
