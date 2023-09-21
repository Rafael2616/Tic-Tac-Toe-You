package com.rafael.tictactoe.game.domain.util

import android.app.Activity
import com.rafael.tictactoe.common.domain.util.ScreenSizeGetter

open class Coordinates(val activity: Activity) {
    val screenSize = ScreenSizeGetter(activity)
    val width = screenSize.first
    val height = screenSize.second

    val widthCenter = (width / 2).toFloat()
    val heightCenter = (height / 2).toFloat()

    val horizontalStartX = (0.02 * width).toFloat()
    val horizontalEndX = (width - (0.02 * width)).toFloat()

    val secondHorizontalY = (heightCenter + (0.16 * height)).toFloat()
    val tertiaryHorizontalY = (secondHorizontalY + (0.16 * height)).toFloat()

    val thirtThreePercentOfWidth = (width / 3).toFloat()

    val firstVerticalStartAndEndX = (thirtThreePercentOfWidth / 1.85).toFloat()
    val tertiaryVerticalStartAndEndX = (width - (0.185 * width)).toFloat()

    val endHeight = (height - (0.05 * height)).toFloat()

    val verticalAndTransversalStartY = (heightCenter - (0.08 * height)).toFloat()
    val transversalEndY = (endHeight - (0.05 * height)).toFloat()
}