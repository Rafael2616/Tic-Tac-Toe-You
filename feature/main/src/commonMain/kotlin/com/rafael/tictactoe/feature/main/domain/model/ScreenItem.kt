package com.rafael.tictactoe.feature.main.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource

internal data class ScreenItem(
    val icon: ImageVector,
    val tint: Color,
    val text: StringResource,
    val onClick: () -> Unit,
)
