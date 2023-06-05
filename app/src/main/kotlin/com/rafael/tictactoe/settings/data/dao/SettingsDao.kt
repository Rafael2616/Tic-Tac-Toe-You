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
suspend fun getSettingsEntity(): SettingsEntity

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun setSettingsEntity(settingsEntity: SettingsEntity)


@Query("UPDATE SettingsEntity SET isBackgroundMusicOn = :isBackgroundMusicOn")
suspend fun setIsBackgroundMusicOnValue(isBackgroundMusicOn: Boolean)

@Query("UPDATE SettingsEntity SET isSoundEffectsOn = :isSoundEffectsOn")
suspend fun setIsSoundEffectsOnValue(isSoundEffectsOn: Boolean)

@Query("UPDATE SettingsEntity SET isDynamicColorsOn = :isDynamicColorsOn")
suspend fun setIsDynamicColorsOnValue(isDynamicColorsOn: Boolean)

@Query("UPDATE SettingsEntity SET isDarkThemeOn = :isDarkThemeOn")
suspend fun setIsDarkThemeOnValue(isDarkThemeOn: Boolean)

@Query("UPDATE SettingsEntity SET isFollowingSystemOn = :isFollowingSystemOn")
suspend fun setIsFollowingSystemOnValue(isFollowingSystemOn: Boolean)


@Query("SELECT isBackgroundMusicOn FROM SettingsEntity LIMIT 1")
suspend fun getBackgroundMusicValue(): Boolean

@Query("SELECT isSoundEffectsOn FROM SettingsEntity LIMIT 1")
suspend fun getSoundEffectsValue(): Boolean

@Query("SELECT isDynamicColorsOn FROM SettingsEntity LIMIT 1")
suspend fun getDynamicColorsValue(): Boolean

@Query("SELECT isDarkThemeOn FROM SettingsEntity LIMIT 1")
suspend fun getDarkThemeValue(): Boolean

@Query("SELECT isFollowingSystemOn FROM SettingsEntity LIMIT 1")
suspend fun getFollowingSystemValue(): Boolean

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