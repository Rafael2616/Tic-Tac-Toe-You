package com.rafael.tictactoe.game.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Level(
    val level: CPUDifficultyLevel,
    val name: Int,
    val icon: ImageVector,
    val tint: Color,
)
