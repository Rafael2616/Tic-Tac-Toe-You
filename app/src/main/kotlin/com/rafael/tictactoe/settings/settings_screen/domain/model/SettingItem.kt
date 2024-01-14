package com.rafael.tictactoe.settings.settingsScreen.domain.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.model.SearchItemSpecs
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.SettingsGroup

data class SettingItem(
    val leadingIcon: ImageVector? = null,
    val trailingIcon: ImageVector? = null,
    val isChecked: Boolean? = null,
    val index: Int,
    val groupType: SettingsGroup,
    val searchSpecs: SearchItemSpecs,
)
