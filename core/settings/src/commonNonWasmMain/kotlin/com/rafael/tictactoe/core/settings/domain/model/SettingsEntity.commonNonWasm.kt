package com.rafael.tictactoe.core.settings.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
actual data class SettingsEntity actual constructor(
    @PrimaryKey
    actual var id: Int,
    actual var isBackgroundMusicOn: Boolean,
    actual var isDarkThemeOn: Boolean,
    actual var isDynamicColorsOn: Boolean,
    actual var isFollowingSystemOn: Boolean,
    actual var isSoundEffectsOn: Boolean,
    actual var selectedWinnerLineColor: Int,
    actual var selectedXColor: Int,
    actual var selectedCircleColor: Int,
    actual var appVersion: String,
    actual var shapeSelected: Int,
    actual var boardCardsCornerRadius: Float,
    actual var boardCardsThickness: Float,
    actual var boardCardsElevation: Float,
    actual var boardCardsShadow: Float,
    actual var boardCardsBorderColor: String,
    actual var isRGBEffectOn: Boolean,
    actual var isGradientOn: Boolean,
    actual var profilePicture: ByteArray?,
) {
    actual companion object {
        actual fun getDefaultSettings(): SettingsEntity = getCommonDefaultSettings()
    }
}
