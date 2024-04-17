package com.rafael.tictactoe.settings.searchSettingsScreen.domain.model

import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingItem

data class SearchSettingsState(
    val matchingSettings: List<SettingItem> = emptyList(),
    val query: String = "",
)
