package com.rafael.tictactoe.feature.settings.domain.model

import androidx.compose.ui.graphics.Color
import com.rafael.tictactoe.core.domain.util.Colors
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.color_blue
import com.rafael.tictactoe.core.resources.color_burnt_yellow
import com.rafael.tictactoe.core.resources.color_cyan
import com.rafael.tictactoe.core.resources.color_dark_green
import com.rafael.tictactoe.core.resources.color_green
import com.rafael.tictactoe.core.resources.color_pink
import com.rafael.tictactoe.core.resources.color_purple
import com.rafael.tictactoe.core.resources.color_red
import com.rafael.tictactoe.core.resources.color_white
import com.rafael.tictactoe.core.resources.color_yellow
import org.jetbrains.compose.resources.StringResource

data class ColorItem(
    val color: Color,
    val name: StringResource,
) {
    companion object {
        fun getColorItems(): List<ColorItem> {
            val colors = Colors.get()
            return listOf(
                ColorItem(
                    color = colors.blue,
                    name = Res.string.color_blue,
                ),
                ColorItem(
                    color = colors.red,
                    name = Res.string.color_red,
                ),
                ColorItem(
                    color = colors.yellow,
                    name = Res.string.color_yellow,
                ),
                ColorItem(
                    color = colors.burntYellow,
                    name = Res.string.color_burnt_yellow,
                ),
                ColorItem(
                    color = colors.darkGreen,
                    name = Res.string.color_dark_green,
                ),
                ColorItem(
                    color = colors.lightGreen,
                    name = Res.string.color_green,
                ),
                ColorItem(
                    color = colors.purple,
                    name = Res.string.color_purple,
                ),
                ColorItem(
                    color = colors.magenta,
                    name = Res.string.color_pink,
                ),
                ColorItem(
                    color = colors.white,
                    name = Res.string.color_white,
                ),
                ColorItem(
                    color = colors.cyan,
                    name = Res.string.color_cyan,
                ),
            )
        }
    }
}
