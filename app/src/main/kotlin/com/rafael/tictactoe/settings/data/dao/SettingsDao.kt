package com.rafael.tictactoe.settings.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.rafael.tictactoe.settings.domain.model.SettingsEntity

@Dao
interface SettingsDao {

	@Query("SELECT * FROM SettingsEntity LIMIT 1")
	suspend fun getSettingsValues(): SettingsEntity

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun setSettingsValues(settingsEntity: SettingsEntity)
}