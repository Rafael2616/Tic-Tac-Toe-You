package com.rafael.tictactoe.feature.settings.domain.repository

import com.rafael.tictactoe.feature.settings.domain.model.SettingsEntity

interface SettingsRepository {

    suspend fun getAllSettingsValues(id: Int): SettingsEntity?

    suspend fun setAllSettingsValues(settingsEntity: SettingsEntity)

    suspend fun getProfilePicture(): ByteArray?

    suspend fun deleteProfilePicture(profilePicture: ByteArray)
}
