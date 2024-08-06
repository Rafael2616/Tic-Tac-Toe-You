package com.rafael.tictactoe.feature.settingssearch.domain.model

import com.rafael.tictactoe.feature.settings.domain.model.SettingsState

sealed interface SearchSettingsEvent {
    data class QuerySettingsFromRepository(val query: String, val settingsState: SettingsState) :
        SearchSettingsEvent
    data object ResetQuery : SearchSettingsEvent
}
