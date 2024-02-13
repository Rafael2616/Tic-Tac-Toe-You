package com.rafael.tictactoe.settings.searchSettingsScreen.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.model.SearchItemSpecs
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.model.SearchSettingsEvent
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.model.SearchSettingsState
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.repository.SearchSettingsManager
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingItem
import com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodel.SettingsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SearchSettingsViewModel @Inject constructor(
    private val searchManager: SearchSettingsManager,
    private val settingsVM: SettingsViewModel,
    private val context: Application,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchSettingsState())
    val state = _state.asStateFlow()
    var searchJob: Job? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            searchManager.init()
        }

        var allSettings: List<SettingItem> = emptyList()

        for (listSettings in SettingItem.getSettingsItems(settingsVM.state.value, context)) {
            for (setting in listSettings) {
                allSettings += setting
            }
        }
        onEvent(SearchSettingsEvent.PutSettingsToRepository(allSettings))
    }

    fun onEvent(event: SearchSettingsEvent) {
        when (event) {
            is SearchSettingsEvent.PutSettingsToRepository -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val allSettingsItems = (0..event.settings.size - 1).map {
                        SearchItemSpecs(
                            namespace = event.settings[it].searchSpecs.namespace,
                            id = event.settings[it].searchSpecs.id,
                            text = event.settings[it].searchSpecs.text,
                        )
                    }
                    searchManager.putSettings(allSettingsItems)
                    _state.update { it.copy(allSettingsItems = event.settings) }
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
                    val results = searchManager.searchSettings(query)
                    delay(150L)
                    val matchingSettings = state.value.allSettingsItems.filter { setting ->
                        results.any { result -> result.text.contains(setting.searchSpecs.text) }
                    }
                    _state.update { it.copy(matchingSettings = matchingSettings) }
                }
            }
            SearchSettingsEvent.ResetQuery -> {
                _state.update { it.copy(query = "") }
                onEvent(SearchSettingsEvent.QuerySettingsFromRepository(null))
            }
            is SearchSettingsEvent.UpdateMatchingSettings -> {
                _state.update {
                    it.copy(
                        matchingSettings = state.value.matchingSettings.mapIndexed { index, setting ->
                            if (index == event.index) {
                                setting.copy(isChecked = setting.isChecked?.let { !it })
                            } else {
                                setting
                            }
                        },
                    )
                }
                var allSettingsUpdated: List<SettingItem> = emptyList()

                for (listSettings in SettingItem.getSettingsItems(settingsVM.state.value, context)) {
                    for (setting in listSettings) {
                        allSettingsUpdated += setting
                    }
                }
                onEvent(SearchSettingsEvent.PutSettingsToRepository(allSettingsUpdated))
            }
        }
    }

    override fun onCleared() {
        searchManager.closeSession()
        super.onCleared()
    }
}
