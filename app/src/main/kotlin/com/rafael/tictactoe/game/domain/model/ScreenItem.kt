package com.rafael.tictactoe.game.domain.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class ScreenItem(
    val name: String,
    val icon: ImageVector,
    val tint: Color,
    @StringRes val text: Int
)