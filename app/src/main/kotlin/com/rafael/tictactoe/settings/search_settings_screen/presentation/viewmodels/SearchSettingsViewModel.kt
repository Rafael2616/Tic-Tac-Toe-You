package com.rafael.tictactoe.settings.searchSettingsScreen.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.model.SearchItemSpecs
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.model.SearchSettingsEvent
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.model.SearchSettingsState
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.repository.SearchSettingsManager
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingItem
import com.rafael.tictactoe.settings.settingsScreen.presentation.utils.getSettingsItems
import com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodels.SettingsViewModel
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
    var allSettings: List<SettingItem> = emptyList()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            searchManager.init()
        }
        for (listSettings in getSettingsItems(settingsVM.state.value, context)) {
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
                }
            }
            is SearchSettingsEvent.QuerySettingsFromRepository -> {
                _state.update { it.copy(query = event.query) }

                searchJob?.cancel()

                if (event.query.isEmpty() || event.query.isBlank()) {
                    _state.update { it.copy(matchingSettings = emptyList()) }
                    return
                }

                searchJob = viewModelScope.launch {
                    val results = searchManager.searchSettings(event.query)
                    delay(150L)
                    val matchingSettings = allSettings.filter { setting ->
                        results.any { result -> result.text.contains(setting.searchSpecs.text) }
                    }
                    _state.update { it.copy(matchingSettings = matchingSettings) }
                }
            }
            SearchSettingsEvent.ResetQuery -> {
                _state.update { it.copy(query = "") }
            }
        }
    }

    override fun onCleared() {
        searchManager.closeSession()
        super.onCleared()
    }
}
