package com.rafael.tictactoe.core.settings.domain.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.rafael.tictactoe.core.settings.domain.model.BoardConstants.DEF_CARDS_BORDER_THICKNESS
import com.rafael.tictactoe.core.settings.domain.model.BoardConstants.DEF_CARDS_CORNER_RADIUS
import com.rafael.tictactoe.core.settings.domain.model.BoardConstants.DEF_CARDS_ELEVATION
import com.rafael.tictactoe.core.settings.domain.model.BoardConstants.DEF_CARDS_SHADOW

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
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
    var profilePicture: ByteArray?
) {
    companion object {
        fun getDefaultSettings() = SettingsEntity(
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
        profilePicture = null,
        )
    }
}
