package com.rafael.tictactoe.core.domain.util

import androidx.compose.ui.graphics.Color

fun Int.toColor(): Color {
    val color = Colors.get()
    return when (this) {
        0 -> color.blue
        1 -> color.red
        2 -> color.yellow
        3 -> color.burntYellow
        4 -> color.darkGreen
        5 -> color.lightGreen
        6 -> color.purple
        7 -> color.magenta
        8 -> color.white
        else -> color.cyan
    }
}
