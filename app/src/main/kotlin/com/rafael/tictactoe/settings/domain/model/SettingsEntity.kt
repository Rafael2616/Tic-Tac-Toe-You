package com.rafael.tictactoe.settings.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey
	var id: Int = 1,
    var isBackgroundMusicOn: Boolean,
    var isDarkThemeOn: Boolean,
    var isDynamicColorsOn: Boolean,
    var isFollowingSystemOn: Boolean,
    var isSoundEffectsOn: Boolean
) {
    companion object {
        fun getDefaultSettings(): SettingsEntity {
            return SettingsEntity(
				id = 1,
                isBackgroundMusicOn = true,
                isDarkThemeOn = true,
                isDynamicColorsOn = true,
                isFollowingSystemOn = true,
                isSoundEffectsOn = true
            )
        }
    }
}