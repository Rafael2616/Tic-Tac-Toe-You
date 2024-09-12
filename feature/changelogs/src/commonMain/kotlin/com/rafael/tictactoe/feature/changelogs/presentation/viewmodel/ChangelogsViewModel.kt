package com.rafael.tictactoe.feature.changelogs.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafael.tictactoe.core.platform.dispatchers.IO
import com.rafael.tictactoe.feature.changelogs.data.ChangelogGetter
import com.rafael.tictactoe.feature.changelogs.domain.Changelog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChangelogsViewModel : ViewModel() {
    private var _allChangelogs = MutableStateFlow(listOf(Changelog()))
    private var _currentChangelog = MutableStateFlow(Changelog())
    val allChangelogs = _allChangelogs.asStateFlow()
    val currentChangelog = _currentChangelog.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrent()
        }
    }

    fun getCurrent() {
        viewModelScope.launch(Dispatchers.IO) {
            _currentChangelog.update {
                ChangelogGetter.getCurrent()
            }
        }
    }

    fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            _allChangelogs.update {
                ChangelogGetter.getAll()
            }
        }
    }
}
