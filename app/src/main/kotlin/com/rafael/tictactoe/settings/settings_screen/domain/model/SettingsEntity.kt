package com.rafael.tictactoe.settings.settingsScreen.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rafael.tictactoe.settings.settingsScreen.presentation.util.BoardConstants.DEF_CARDS_BORDER_THICKNESS
import com.rafael.tictactoe.settings.settingsScreen.presentation.util.BoardConstants.DEF_CARDS_CORNER_RADIUS
import com.rafael.tictactoe.settings.settingsScreen.presentation.util.BoardConstants.DEF_CARDS_ELEVATION
import com.rafael.tictactoe.settings.settingsScreen.presentation.util.BoardConstants.DEF_CARDS_SHADOW

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
    var isRGBEffectOn: Boolean,
    var isGradientOn: Boolean,
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
                boardCardsCornerRadius = DEF_CARDS_CORNER_RADIUS,
                boardCardsThickness = DEF_CARDS_BORDER_THICKNESS,
                boardCardsElevation = DEF_CARDS_ELEVATION,
                boardCardsShadow = DEF_CARDS_SHADOW,
                boardCardsBorderColor = "FFFFFFFF",
                isRGBEffectOn = false,
                isGradientOn = true,
            )
        }
    }
}
