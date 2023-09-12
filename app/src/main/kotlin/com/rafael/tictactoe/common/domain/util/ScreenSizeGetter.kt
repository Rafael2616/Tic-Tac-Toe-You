package com.rafael.tictactoe.common.domain.util

import android.app.Activity
import android.util.DisplayMetrics

fun ScreenSizeGetter(activity: Activity): Pair<Int, Int> {
    val displaySize = DisplayMetrics()

    @Suppress("DEPRECATION")
    activity.display?.getRealMetrics(displaySize)

    val width = displaySize.widthPixels
    val height = displaySize.heightPixels

    return Pair(width, height)
}