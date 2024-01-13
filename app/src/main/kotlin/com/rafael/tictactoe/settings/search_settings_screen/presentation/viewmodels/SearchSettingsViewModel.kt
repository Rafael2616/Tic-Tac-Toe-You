package com.rafael.tictactoe.settings.searchSettingsScreen.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.model.SearchSettingsEvent
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.model.SearchSettingsState
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.repository.SettingsSearchManager
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingItem
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingItemDoc
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
    private val context: Application,
    private val searchManager: SettingsSearchManager,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchSettingsState())
    val state = _state.asStateFlow()
    var searchJob: Job? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            searchManager.init()
        }
    }

    fun onEvent(event: SearchSettingsEvent) {
        when (event) {
            is SearchSettingsEvent.PutSettingsToRepository -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val allSettingsItemsDoc = (0..event.settings.size - 1).map {
                        SettingItemDoc(
                            namespace = event.settings[it].searchText.namespace,
                            id = event.settings[it].searchText.id,
                            text = event.settings[it].searchText.text,
                        )
                    }
                    val allSettings = (0..event.settings.size - 1).map {
                        event.settings[it]
                    }
                    searchManager.putSettings(allSettingsItemsDoc)
                    _state.update { it.copy(allSettingsItems = allSettings) }
                }
            }
            is SearchSettingsEvent.QuerySettingsFromRepository -> {
                var matchingSettings: List<SettingItem> = emptyList()
                _state.update {
                    it.copy(
                        query = event.query,
                        matchingSettings = emptyList(),
                    )
                }

                searchJob?.cancel()

                if (event.query == "") return

                searchJob = viewModelScope.launch {
                    delay(100L)
                    val results = searchManager.searchSettings(event.query)

                    for (result in results) {
                        for (setting in _state.value.allSettingsItems) {
                            if (result.text.contains(context.getString(setting.text)) &&
                                setting !in matchingSettings
                            ) {
                                matchingSettings += setting
                            }
                        }
                    }
                    _state.update { it.copy(matchingSettings = matchingSettings) }
                }
            }
        }
    }


    override fun onCleared() {
        searchManager.closeSession()
        super.onCleared()
    }
}
