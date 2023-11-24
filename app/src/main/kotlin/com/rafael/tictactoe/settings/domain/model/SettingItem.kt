package com.rafael.tictactoe.settings.domain.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class SettingsItem(
    val leadingIcon: ImageVector? = null,
    val text: Int,
    val trailingIcon: ImageVector? = null,
    val isChecked: Boolean? = null
)