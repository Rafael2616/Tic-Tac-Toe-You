package com.rafael.tictactoe.feature.settingssearch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafael.tictactoe.feature.settings.domain.model.SettingItem
import com.rafael.tictactoe.feature.settingssearch.domain.model.SearchSettingsEvent
import com.rafael.tictactoe.feature.settingssearch.domain.model.SearchSettingsState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchSettingsViewModel : ViewModel() {
    private val _state = MutableStateFlow(SearchSettingsState())
    val state = _state
        .asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            SearchSettingsState(),
        )
    private var searchJob: Job? = null

    fun onEvent(event: SearchSettingsEvent) {
        when (event) {
            is SearchSettingsEvent.QuerySettingsFromRepository -> {
                _state.update { it.copy(query = event.query) }

                searchJob?.cancel()

                if (event.query.isEmpty() || event.query.isBlank()) {
                    _state.update { it.copy(matchingSettings = emptyList()) }
                    return
                }
                searchJob = viewModelScope.launch {
                    // Show all available settings when typing "."
                    if (event.query == ".") {
                        _state.update {
                            it.copy(
                                matchingSettings = SettingItem.getSettingsItems(event.settingsState)
                                    .filter { setting -> setting.isAvailable },
                            )
                        }
                        return@launch
                    }
                    delay(125L)
                    val allSettings = SettingItem.getSettingsItems(event.settingsState)
                    val matchingSettings = allSettings.filter { setting ->
                        allSettings.any {
                            setting.searchSpecs.text.contains(
                                event.query,
                                ignoreCase = true,
                            )
                        }
                    }
                    val availableMatchingSettings = matchingSettings.filter { it.isAvailable }
                    _state.update { it.copy(matchingSettings = availableMatchingSettings) }
                }
            }
            SearchSettingsEvent.ResetQuery -> {
                _state.update {
                    it.copy(
                        query = "",
                        matchingSettings = emptyList(),
                    )
                }
            }
        }
    }
}
