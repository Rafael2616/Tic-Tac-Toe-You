package com.rafael.tictactoe.settings.domain.repository

import com.rafael.tictactoe.settings.domain.model.SettingsEntity
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun getAllSettingsValues(id: Int): Flow<SettingsEntity?>
	
    suspend fun setAllSettingsValues(settingsEntity: SettingsEntity)
}
