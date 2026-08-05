package com.rafael.tictactoe.core.settings.data.repository

import com.rafael.tictactoe.core.settings.domain.model.SettingsEntity
import com.rafael.tictactoe.core.settings.domain.repository.SettingsRepository

class InMemorySettingsRepository : SettingsRepository {
    private var settings: SettingsEntity = SettingsEntity.getDefaultSettings()

    override suspend fun getAllSettingsValues(id: Int): SettingsEntity? {
        return settings
    }

    override suspend fun setAllSettingsValues(settingsEntity: SettingsEntity) {
        this.settings = settingsEntity
    }

    override suspend fun getProfilePicture(): ByteArray? {
        return settings.profilePicture
    }

    override suspend fun deleteProfilePicture(profilePicture: ByteArray) {
        settings.profilePicture = null
    }
}
