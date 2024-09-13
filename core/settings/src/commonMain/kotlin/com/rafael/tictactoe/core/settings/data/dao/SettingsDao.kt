package com.rafael.tictactoe.core.settings.data.dao

import com.rafael.tictactoe.core.settings.domain.model.SettingsEntity

expect interface SettingsDao {

    suspend fun getSettingsValues(id: Int): SettingsEntity?

    suspend fun setAllSettingsValues(settingsEntity: SettingsEntity)

    suspend fun getProfilePicture(): ByteArray?

    suspend fun deleteProfilePicture(profilePicture: ByteArray)
}
