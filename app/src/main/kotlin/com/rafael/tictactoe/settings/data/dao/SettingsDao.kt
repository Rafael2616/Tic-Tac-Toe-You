package com.rafael.tictactoe.settings.data.dao

import androidx.room.Dao
import androidx.room.Upsert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.rafael.tictactoe.settings.domain.model.SettingsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {

	@Query("SELECT * FROM settings WHERE id = :id")
	suspend fun getSettingsValues(id: Int): SettingsEntity?

	@Upsert
	suspend fun setAllSettingsValues(settingsEntity: SettingsEntity)
}