package com.rafael.tictactoe.feature.main.domain.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

internal data class ScreenItem(
    val icon: ImageVector,
    val tint: Color,
    @StringRes val text: Int,
    val onClick: () -> Unit,
)
