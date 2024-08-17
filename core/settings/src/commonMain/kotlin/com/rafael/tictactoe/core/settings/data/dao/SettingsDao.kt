package com.rafael.tictactoe.core.settings.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.rafael.tictactoe.core.settings.domain.model.SettingsEntity

@Dao
interface SettingsDao {

    @Query("SELECT * FROM settings WHERE id = :id")
    suspend fun getSettingsValues(id: Int): SettingsEntity?

    @Upsert
    suspend fun setAllSettingsValues(settingsEntity: SettingsEntity)

    @Query("SELECT profilePicture FROM settings WHERE id = 1")
    suspend fun getProfilePicture(): ByteArray?

    @Query("DELETE FROM settings WHERE profilePicture = :profilePicture")
    suspend fun deleteProfilePicture(profilePicture: ByteArray)
}
