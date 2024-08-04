package com.rafael.tictactoe.feature.settings.domain.model

// import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class BoardShape(
    val name: String,
    val cornerRadius: Float,
    val elevation: Float = 15f,
    val shadow: Float = 0f,
    val thickness: Float = 0f,
    val borderColor: Color = Color.White,
    val borderHexColor: String = "FFFFFFFF",
)
