package com.rafael.tictactoe.settings.settingsScreen.domain.model

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
    var isSoundEffectsOn: Boolean,
    var selectedWinnerLineColor: Int,
    var selectedXColor: Int,
    var selectedCircleColor: Int,
    var appVersion: String,
    var shapeSelected: Int,
    var boardCardsCornerRadius: Float,
    var boardCardsThickness: Float,
    var boardCardsElevation: Float,
    var boardCardsShadow: Float,
    var boardCardsBorderColor: String,
) {
    companion object {
        fun getDefaultSettings(): SettingsEntity {
            return SettingsEntity(
                id = 1,
                isBackgroundMusicOn = true,
                isDarkThemeOn = true,
                isDynamicColorsOn = true,
                isFollowingSystemOn = true,
                isSoundEffectsOn = true,
                selectedWinnerLineColor = 2,
                selectedXColor = 1,
                selectedCircleColor = 5,
                appVersion = "0",
                shapeSelected = 1,
                boardCardsCornerRadius = 10f,
                boardCardsThickness = 0f,
                boardCardsElevation = 20f,
                boardCardsShadow = 0f,
                boardCardsBorderColor = "FFFFFFFF",
            )
        }
    }
}
