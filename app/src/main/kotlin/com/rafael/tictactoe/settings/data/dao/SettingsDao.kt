package com.rafael.tictactoe.settings.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import com.rafael.tictactoe.settings.domain.model.SettingsEntity

@Dao
interface SettingsDao {

@Query("SELECT * FROM SettingsEntity LIMIT 1")
suspend fun getAllSettingsValues(): SettingsEntity

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun setAllSettingsValues(settingsEntity: SettingsEntity)

/*//I'II KEEP THIS AS TEMPLATE
@Query("UPDATE SettingsEntity SET isBackgroundMusicOn = :isBackgroundMusicOn")
suspend fun setIsBackgroundMusicOn(isBackgroundMusicOn: Boolean)
*/

}