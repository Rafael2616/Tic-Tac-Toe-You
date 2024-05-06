package com.rafael.tictactoe.settings.settingsScreen.domain.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class BoardShape(
    @StringRes val name: Int,
    val cornerRadius: Float,
    val elevation: Float = 15f,
    val shadow: Float = 0f,
    val thickness: Float = 0f,
    val borderColor: Color = Color.White,
    val borderHexColor: String = "FFFFFFFF",
)
