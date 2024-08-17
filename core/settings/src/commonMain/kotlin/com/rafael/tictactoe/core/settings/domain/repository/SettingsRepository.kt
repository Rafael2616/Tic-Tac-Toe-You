package com.rafael.tictactoe.core.settings.domain.repository

import com.rafael.tictactoe.core.settings.domain.model.SettingsEntity

interface SettingsRepository {

    suspend fun getAllSettingsValues(id: Int): SettingsEntity?

    suspend fun setAllSettingsValues(settingsEntity: SettingsEntity)

    suspend fun getProfilePicture(): ByteArray?

    suspend fun deleteProfilePicture(profilePicture: ByteArray)
}
