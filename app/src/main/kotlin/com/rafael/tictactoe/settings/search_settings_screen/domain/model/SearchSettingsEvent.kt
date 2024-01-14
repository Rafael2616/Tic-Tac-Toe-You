package com.rafael.tictactoe.settings.searchSettingsScreen.domain.model

import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingItem

sealed interface SearchSettingsEvent {
    data class PutSettingsToRepository(val settings: List<SettingItem>) : SearchSettingsEvent
    data class QuerySettingsFromRepository(val query: String) : SearchSettingsEvent
    object ResetQuery : SearchSettingsEvent
}
