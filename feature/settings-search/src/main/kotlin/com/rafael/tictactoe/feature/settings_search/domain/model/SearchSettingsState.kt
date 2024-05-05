package com.rafael.tictactoe.feature.settingssearch.domain.model

import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingItem

data class SearchSettingsState(
    val matchingSettings: List<SettingItem> = emptyList(),
    val query: String = "",
)
