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


@Query("UPDATE SettingsEntity SET isBackgroundMusicOn = :isBackgroundMusicOn")
suspend fun setIsBackgroundMusicOn(isBackgroundMusicOn: Boolean)

@Query("UPDATE SettingsEntity SET isSoundEffectsOn = :isSoundEffectsOn")
suspend fun setIsSoundEffectsOn(isSoundEffectsOn: Boolean)

@Query("UPDATE SettingsEntity SET isDynamicColorsOn = :isDynamicColorsOn")
suspend fun setIsDynamicColorsOn(isDynamicColorsOn: Boolean)

@Query("UPDATE SettingsEntity SET isDarkThemeOn = :isDarkThemeOn")
suspend fun setIsDarkThemeOn(isDarkThemeOn: Boolean)

@Query("UPDATE SettingsEntity SET isFollowingSystemOn = :isFollowingSystemOn")
suspend fun setIsFollowingSystemOn(isFollowingSystemOn: Boolean)


@Query("SELECT isBackgroundMusicOn FROM SettingsEntity LIMIT 1")
suspend fun getIsBackgroundMusicOn(): Boolean

@Query("SELECT isSoundEffectsOn FROM SettingsEntity LIMIT 1")
suspend fun getIsSoundEffectsOn(): Boolean

@Query("SELECT isDynamicColorsOn FROM SettingsEntity LIMIT 1")
suspend fun getIsDynamicColorsOn(): Boolean

@Query("SELECT isDarkThemeOn FROM SettingsEntity LIMIT 1")
suspend fun getIsDarkThemeOn(): Boolean

@Query("SELECT isFollowingSystemOn FROM SettingsEntity LIMIT 1")
suspend fun getIsFollowingSystemOn(): Boolean

}

/*
@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun setIsBackgroundMusicOnValue(isBackgroundMusicOn: SettingsEntity)

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun setIsSoundEffectsOnValue(isSoundEffectsOn: SettingsEntity)

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun setIsDynamicColorsOnValue(isDynamicColorsOn: SettingsEntity)

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun setIsDarkThemeOnValue(isDarkThemeOn: SettingsEntity)

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun setIsFollowingSystemOnValue(isFollowingSystemOn: SettingsEntity)
*/