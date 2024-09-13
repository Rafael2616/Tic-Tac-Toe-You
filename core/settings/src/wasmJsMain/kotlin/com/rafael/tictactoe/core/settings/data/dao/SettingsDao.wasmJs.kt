package com.rafael.tictactoe.core.settings.data.dao

import com.rafael.tictactoe.core.settings.domain.model.SettingsEntity

actual interface SettingsDao {

    actual suspend fun getSettingsValues(id: Int): SettingsEntity?

    actual suspend fun setAllSettingsValues(settingsEntity: SettingsEntity)

    actual suspend fun getProfilePicture(): ByteArray?

    actual suspend fun deleteProfilePicture(profilePicture: ByteArray)
}
