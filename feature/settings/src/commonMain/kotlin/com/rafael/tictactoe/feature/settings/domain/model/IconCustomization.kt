package com.rafael.tictactoe.feature.settings.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.rafael.tictactoe.core.settings.domain.enums.Customization

data class IconCustomization(
    val label: Customization,
    val name: String,
    val selectedIndex: Int,
    val color: Color,
    val isSelected: (Int) -> Boolean,
    val drawIcon: DrawScope.() -> Unit,
    val onSelectIndex: (Int) -> Unit,
    val onSelect: (Color) -> Unit,
    val onApply: () -> Unit,
)
