package com.rafael.tictactoe.feature.settingssearch.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafael.tictactoe.feature.settings.domain.model.SearchItemSpecs
import com.rafael.tictactoe.feature.settings.domain.model.SettingItem
import com.rafael.tictactoe.feature.settings.presentation.viewmodel.SettingsViewModel
import com.rafael.tictactoe.feature.settingssearch.data.repository.AndroidSearchSettingsManager
import com.rafael.tictactoe.feature.settingssearch.domain.model.SearchSettingsEvent
import com.rafael.tictactoe.feature.settingssearch.domain.model.SearchSettingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SearchSettingsViewModel @Inject constructor(
    private val searchManager: AndroidSearchSettingsManager,
    private val settingsVM: SettingsViewModel,
    private val context: Application,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchSettingsState())
    val state = _state
        .asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            SearchSettingsState(),
        )
    var searchJob: Job? = null

    init {
        viewModelScope.launch(Dispatchers.IO) { searchManager.init() }

        var allSettings: List<SettingItem> = emptyList()

        for (setting in SettingItem.getSettingsItems(context, settingsVM.state.value)) {
            allSettings += setting
        }
        onEvent(SearchSettingsEvent.PutSettingsToRepository(allSettings))
    }

    fun onEvent(event: SearchSettingsEvent) {
        when (event) {
            is PutSettingsToRepository -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val allSettingsItems = (0 until event.settings.size).map {
                        SearchItemSpecs(
                            namespace = event.settings[it].searchSpecs.namespace,
                            id = event.settings[it].searchSpecs.id,
                            text = event.settings[it].searchSpecs.text,
                        )
                    }
                    searchManager.putSettings(allSettingsItems)
                    onEvent(SearchSettingsEvent.QuerySettingsFromRepository(null))
                }
            }
            is QuerySettingsFromRepository -> {
                val query = event.query ?: state.value.query

                _state.update { it.copy(query = query) }

                searchJob?.cancel()

                if (query.isEmpty() || query.isBlank()) {
                    _state.update { it.copy(matchingSettings = emptyList()) }
                    return
                }

                searchJob = viewModelScope.launch {
                    val results = searchManager.searchSettings(query)
                    delay(125L)
                    val matchingSettings = SettingItem.getSettingsItems(context, settingsVM.state.value).filter { setting ->
                        results.any { result -> result.text.contains(setting.searchSpecs.text) }
                    }
                    val availableMatchingSettings = matchingSettings.filter { it.isAvailable }
                    _state.update { it.copy(matchingSettings = availableMatchingSettings) }
                }
            }
            ResetQuery -> {
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
