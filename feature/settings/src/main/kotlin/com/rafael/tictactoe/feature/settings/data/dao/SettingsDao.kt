package com.rafael.tictactoe.settings.settingsScreen.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEntity

@Dao
interface SettingsDao {

    @Query("SELECT * FROM settings WHERE id = :id")
    suspend fun getSettingsValues(id: Int): SettingsEntity?

    @Upsert
    suspend fun setAllSettingsValues(settingsEntity: SettingsEntity)
}
