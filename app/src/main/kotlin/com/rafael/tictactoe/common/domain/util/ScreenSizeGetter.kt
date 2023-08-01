package com.rafael.tictactoe.common.domain.util

import android.app.Activity
import android.view.WindowManager
import android.util.DisplayMetrics

fun ScreenSizeGetter(activity: Activity): Pair<Int, Int> {

    val displaySize = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(displaySize)
    val width = displaySize.widthPixels
    val height = displaySize.heightPixels
	
    return Pair(width, height)
} 