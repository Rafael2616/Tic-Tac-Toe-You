package com.rafael.tictactoe.feature.game.domain.model

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource

data class SoundItem(
    val name: StringResource,
    val icon: ImageVector,
    val onClick: () -> Unit,
)
