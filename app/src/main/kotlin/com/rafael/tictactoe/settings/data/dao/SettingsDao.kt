package com.rafael.tictactoe.settings.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Upsert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.rafael.tictactoe.settings.domain.model.SettingsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {

	@Query("SELECT * FROM SettingsEntity WHERE id = :id")
	fun getSettingsValues(id: Int): Flow<SettingsEntity?>

	@Upsert
	suspend fun setSettingsValues(settingsEntity: SettingsEntity)
<<<<<<< HEAD
}
=======
}
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
