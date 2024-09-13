package com.rafael.tictactoe.core.settings.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.rafael.tictactoe.core.settings.domain.model.SettingsEntity

@Dao
actual interface SettingsDao {

    @Query("SELECT * FROM settings WHERE id = :id")
    actual suspend fun getSettingsValues(id: Int): SettingsEntity?

    @Upsert
    actual suspend fun setAllSettingsValues(settingsEntity: SettingsEntity)

    @Query("SELECT profilePicture FROM settings WHERE id = 1")
    actual suspend fun getProfilePicture(): ByteArray?

    @Query("DELETE FROM settings WHERE profilePicture = :profilePicture")
    actual suspend fun deleteProfilePicture(profilePicture: ByteArray)
}
