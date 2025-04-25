package com.rafael.tictactoe.core.settings.data.repository

import com.rafael.tictactoe.core.settings.data.dao.SettingsDao
import com.rafael.tictactoe.core.settings.domain.model.SettingsEntity
import com.rafael.tictactoe.core.settings.domain.repository.SettingsRepository

class SettingsRepositoryImpl(
    private val settingsDao: SettingsDao,
) : SettingsRepository {

    init {
        println("Settings repository has been initialized")
    }

    override suspend fun getAllSettingsValues(id: Int): SettingsEntity? = settingsDao.getSettingsValues(id)

    override suspend fun setAllSettingsValues(settingsEntity: SettingsEntity) {
        settingsDao.setAllSettingsValues(settingsEntity)
    }

    override suspend fun getProfilePicture(): ByteArray? = settingsDao.getProfilePicture()

    override suspend fun deleteProfilePicture(profilePicture: ByteArray) {
        settingsDao.deleteProfilePicture(profilePicture)
    }
}
