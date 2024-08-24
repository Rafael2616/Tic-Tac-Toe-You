package com.rafael.tictactoe.feature.settingssearch.domain.model

import com.rafael.tictactoe.core.domain.model.Event
import com.rafael.tictactoe.core.settings.domain.model.SettingsState

sealed interface SearchSettingsEvent : Event {
    data class QuerySettingsFromRepository(val query: String, val settingsState: SettingsState) :
        SearchSettingsEvent
    data object ResetQuery : SearchSettingsEvent
}
