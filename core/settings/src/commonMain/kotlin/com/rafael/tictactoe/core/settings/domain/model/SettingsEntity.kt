package com.rafael.tictactoe.core.settings.domain.model

@androidx.room.Entity(tableName = "settings")
data class SettingsEntity(
    @androidx.room.PrimaryKey
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
    var profilePicture: ByteArray?,
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
                boardCardsCornerRadius = BoardConstants.DEF_CARDS_CORNER_RADIUS,
                boardCardsThickness = BoardConstants.DEF_CARDS_BORDER_THICKNESS,
                boardCardsElevation = BoardConstants.DEF_CARDS_ELEVATION,
                boardCardsShadow = BoardConstants.DEF_CARDS_SHADOW,
                boardCardsBorderColor = "FFFFFFFF",
                isRGBEffectOn = false,
                isGradientOn = true,
                profilePicture = null,
            )
        }
    }
}
