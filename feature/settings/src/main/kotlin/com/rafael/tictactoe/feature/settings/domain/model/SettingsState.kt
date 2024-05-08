package com.rafael.tictactoe.feature.settings.domain.model

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.ui.graphics.Color
import com.rafael.tictactoe.core.domain.util.getVersionName
import com.rafael.tictactoe.core.domain.model.Colors
import com.rafael.tictactoe.feature.settings.domain.enums.Customization
import com.rafael.tictactoe.feature.settings.presentation.util.BoardConstants.DEF_CARDS_BORDER_THICKNESS
import com.rafael.tictactoe.feature.settings.presentation.util.BoardConstants.DEF_CARDS_COLOR
import com.rafael.tictactoe.feature.settings.presentation.util.BoardConstants.DEF_CARDS_CORNER_RADIUS
import com.rafael.tictactoe.feature.settings.presentation.util.BoardConstants.DEF_CARDS_ELEVATION
import com.rafael.tictactoe.feature.settings.presentation.util.BoardConstants.DEF_CARDS_SHADOW

data class SettingsState(
    val color: Colors = Colors.get(),
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
    val circleColor: Color = color.light_green,
    val selectedCircleColor: Int = 5,
    val isBMPlaying: Boolean = false,
    val customizationType: Customization = Customization.XCOLOR,
    val appVersion: String = getVersionName(),
    val showWhatsNewDialog: Boolean = false,
    val showProfilePictureMenu: Boolean = false,
    val profilePictureBitmap: Bitmap? = null,
    val profilePictureUri: Uri = Uri.EMPTY,
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
