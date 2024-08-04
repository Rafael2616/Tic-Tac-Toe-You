package com.rafael.tictactoe.feature.settingssearch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafael.tictactoe.feature.settings.domain.model.SearchItemSpecs
import com.rafael.tictactoe.feature.settings.domain.model.SettingItem
import com.rafael.tictactoe.feature.settings.presentation.viewmodel.SettingsViewModel
import com.rafael.tictactoe.feature.settingssearch.domain.model.SearchSettingsEvent
import com.rafael.tictactoe.feature.settingssearch.domain.model.SearchSettingsState
import com.rafael.tictactoe.feature.settingssearch.domain.repository.SearchSettingsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchSettingsViewModel(
    private val searchManager: SearchSettingsManager,
    private val settingsVM: SettingsViewModel,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchSettingsState())
    val state = _state
        .asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            SearchSettingsState(),
        )
    private var searchJob: Job? = null

    init {
        val allSettings: MutableList<SettingItem> = mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            searchManager.init()

            SettingItem.getSettingsItems(settingsVM.state.value).also { settings ->
                allSettings.addAll(settings)
            }
        }.invokeOnCompletion {
            onEvent(SearchSettingsEvent.PutSettingsToRepository(allSettings))
        }
    }

    fun onEvent(event: SearchSettingsEvent) {
        when (event) {
            is SearchSettingsEvent.PutSettingsToRepository -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val allSettingsItems = (0 until event.settings.size).map {
                        SearchItemSpecs(
                            _namespace = event.settings[it].searchSpecs.namespace,
                            _id = event.settings[it].searchSpecs.id,
                            _text = event.settings[it].searchSpecs.text,
                        )
                    }
                    searchManager.putSettings(allSettingsItems)
                    onEvent(SearchSettingsEvent.QuerySettingsFromRepository(null))
                }
            }
            is SearchSettingsEvent.QuerySettingsFromRepository -> {
                val query = event.query ?: state.value.query

                _state.update { it.copy(query = query) }

                searchJob?.cancel()

                if (query.isEmpty() || query.isBlank()) {
                    _state.update { it.copy(matchingSettings = emptyList()) }
                    return
                }
                searchJob = viewModelScope.launch {
                    // Show all available settings when typing "."
                    if (query == ".") {
                        _state.update {
                            it.copy(
                                matchingSettings = SettingItem.getSettingsItems(settingsVM.state.value)
                                    .filter { setting -> setting.isAvailable },
                            )
                        }
                        return@launch
                    }

                    val results = searchManager.searchSettings(query)
                    delay(125L)
                    val matchingSettings = SettingItem.getSettingsItems(settingsVM.state.value).filter { setting ->
                        results.any { it.text == setting.searchSpecs.text }
                    }
                    val availableMatchingSettings = matchingSettings.filter { it.isAvailable }
                    _state.update { it.copy(matchingSettings = availableMatchingSettings) }
                }
            }

            SearchSettingsEvent.ResetQuery -> {
                _state.update { it.copy(query = "") }
                onEvent(SearchSettingsEvent.QuerySettingsFromRepository(null))
            }
        }
    }

    override fun onCleared() {
        searchManager.closeSession()
        super.onCleared()
    }
}
