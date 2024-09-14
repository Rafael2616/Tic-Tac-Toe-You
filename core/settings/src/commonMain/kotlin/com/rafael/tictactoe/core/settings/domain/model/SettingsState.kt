package com.rafael.tictactoe.core.settings.domain.model

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import com.rafael.tictactoe.core.domain.util.BuildInfo.VERSION_NAME
import com.rafael.tictactoe.core.domain.util.Colors
import com.rafael.tictactoe.core.settings.domain.enums.Customization
import com.rafael.tictactoe.core.settings.domain.model.BoardConstants.DEF_CARDS_BORDER_THICKNESS
import com.rafael.tictactoe.core.settings.domain.model.BoardConstants.DEF_CARDS_COLOR
import com.rafael.tictactoe.core.settings.domain.model.BoardConstants.DEF_CARDS_CORNER_RADIUS
import com.rafael.tictactoe.core.settings.domain.model.BoardConstants.DEF_CARDS_ELEVATION
import com.rafael.tictactoe.core.settings.domain.model.BoardConstants.DEF_CARDS_SHADOW

@Stable
data class SettingsState(
    val color: Colors = Colors.Companion.get(),
    val isBackgroundMusicOn: Boolean = false,
    val isDarkThemeOn: Boolean = true,
    val isDynamicColorsOn: Boolean = true,
    val isFollowingSystemOn: Boolean = true,
    val isSoundEffectsOn: Boolean = true,
    val isAboutShown: Boolean = false,
    val showColorSelectionDialog: Boolean = false,
    val winnerLineColor: Color = color.yellow,
    val selectedWinnerLineColor: Int = 2,
    val xColor: Color = color.red,
    val selectedXColor: Int = 1,
    val circleColor: Color = color.lightGreen,
    val selectedCircleColor: Int = 5,
    val customizationType: Customization = Customization.XCOLOR,
    val appVersion: String = VERSION_NAME,
    val showWhatsNewDialog: Boolean = false,
    val showProfilePictureMenu: Boolean = false,
    val profilePictureBitmap: ImageBitmap? = null,
    val showBoardCustomizationDialog: Boolean = false,
    val shapeSelected: Int = 1,
    val boardCardsCornerRadius: Float = DEF_CARDS_CORNER_RADIUS,
    val boardCardsThickness: Float = DEF_CARDS_BORDER_THICKNESS,
    val boardCardsElevation: Float = DEF_CARDS_ELEVATION,
    val boardCardsShadow: Float = DEF_CARDS_SHADOW,
    val boardCardsBorderColor: Color = DEF_CARDS_COLOR,
    val isRGBEffectOn: Boolean = false,
    val isGradientOn: Boolean = true,
)
