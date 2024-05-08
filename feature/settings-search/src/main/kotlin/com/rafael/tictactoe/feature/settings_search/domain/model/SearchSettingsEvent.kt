package com.rafael.tictactoe.feature.settingssearch.domain.model

import com.rafael.tictactoe.feature.settings.domain.model.SettingItem

sealed interface SearchSettingsEvent {
    data class PutSettingsToRepository(val settings: List<SettingItem>) : SearchSettingsEvent
    data class QuerySettingsFromRepository(val query: String? = "") : SearchSettingsEvent
    object ResetQuery : SearchSettingsEvent
}
