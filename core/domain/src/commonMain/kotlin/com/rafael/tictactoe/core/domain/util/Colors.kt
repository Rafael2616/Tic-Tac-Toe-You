package com.rafael.tictactoe.core.domain.util

import androidx.compose.ui.graphics.Color

data class Colors(
    val blue: Color,
    val red: Color,
    val yellow: Color,
    val darkGreen: Color,
    val lightGreen: Color,
    val purple: Color,
    val magenta: Color,
    val white: Color,
    val cyan: Color,
    val burntYellow: Color,
    val transparent: Color,
) {
    companion object {
        fun get(): Colors = Colors(
            blue = Color(0xFF1A73E8),
            red = Color.Red,
            yellow = Color.Yellow,
            darkGreen = Color(0xFF299961),
            lightGreen = Color.Green,
            purple = Color(0xFF820AD1),
            magenta = Color.Magenta,
            white = Color.White,
            cyan = Color(0xFF00E8FF),
            burntYellow = Color(0xFFFBBC04),
            transparent = Color.Transparent,
        )
    }
}

private val colors = Colors.get()
val COLORLIST = listOf(
    colors.blue,
    colors.red,
    colors.yellow,
    colors.darkGreen,
    colors.lightGreen,
    colors.purple,
    colors.magenta,
    colors.white,
    colors.cyan,
    colors.burntYellow,
    colors.transparent,
)
