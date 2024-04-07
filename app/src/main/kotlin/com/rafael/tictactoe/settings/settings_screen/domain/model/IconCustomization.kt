package com.rafael.tictactoe.settings.settingsScreen.domain.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.Customization

data class IconCustomization(
    val label: Customization,
    @StringRes val name: Int,
    val selectedIndex: Int,
    val color: Color,
    val isSelected: (Int) -> Boolean,
    val drawIcon: DrawScope.() -> Unit,
    val onSelectIndex: (Int) -> Unit,
    val onSelect: (Color) -> Unit,
    val onApply: () -> Unit,
)
