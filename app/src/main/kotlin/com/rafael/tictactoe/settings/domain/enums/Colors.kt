package com.rafael.tictactoe.settings.domain.enums

import androidx.compose.ui.graphics.Color

data class Colors(
    val blue: Color,
    val red: Color,
    val yellow: Color,
    val dark_green: Color,
    val light_green: Color,
    val purple: Color,
    val magenta: Color,
    val white: Color,
    val cyan: Color,
    val transparent: Color,
    val orange: Color
) {
    companion object {
        fun get(): Colors {
            return Colors(
                blue = Color(0xFF1A73E8),
                red = Color.Red,
                yellow = Color.Yellow,
                dark_green = Color(0xFF299961),
                light_green = Color.Green,
                purple = Color(0xFF820AD1),
                magenta = Color.Magenta,
                white = Color.White,
                cyan = Color.Cyan,
                orange = Color(0xFFFBBC04),
                transparent = Color.Transparent
            )   
        }
    }
}