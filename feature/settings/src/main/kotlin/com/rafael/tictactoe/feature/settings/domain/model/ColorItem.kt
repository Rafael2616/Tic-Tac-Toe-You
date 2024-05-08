package com.rafael.tictactoe.feature.settings.domain.model

import androidx.compose.ui.graphics.Color
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.core.domain.model.Colors

data class ColorItem(
    val color: Color,
    val name: Int,
) {
    companion object {
        fun getColorItems(): List<ColorItem> {
            val colors = Colors.get()
            return listOf(
                ColorItem(
                    color = colors.blue,
                    name = R.string.color_blue,
                ),
                ColorItem(
                    color = colors.red,
                    name = R.string.color_red,
                ),
                ColorItem(
                    color = colors.yellow,
                    name = R.string.color_yellow,
                ),
                ColorItem(
                    color = colors.burnt_yellow,
                    name = R.string.color_burnt_yellow,
                ),
                ColorItem(
                    color = colors.dark_green,
                    name = R.string.color_dark_green,
                ),
                ColorItem(
                    color = colors.light_green,
                    name = R.string.color_green,
                ),
                ColorItem(
                    color = colors.purple,
                    name = R.string.color_purple,
                ),
                ColorItem(
                    color = colors.magenta,
                    name = R.string.color_pink,
                ),
                ColorItem(
                    color = colors.white,
                    name = R.string.color_white,
                ),
                ColorItem(
                    color = colors.cyan,
                    name = R.string.color_cyan,
                ),
            )
        }
    }
}
