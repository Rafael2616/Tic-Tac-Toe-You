package com.rafael.tictactoe.settings.settingsScreen.domain.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class SettingItem(
    val leadingIcon: ImageVector? = null,
    @StringRes val text: Int,
    val trailingIcon: ImageVector? = null,
    val isChecked: Boolean? = null,
    val groupType: GroupType,
    val searchText: SettingItemDoc,
)
