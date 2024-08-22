package com.rafael.tictactoe.feature.changelogs.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafael.tictactoe.feature.changelogs.data.ChangelogGetter
import com.rafael.tictactoe.feature.changelogs.domain.Changelog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChangelogsViewModel : ViewModel() {
    private var _allChangelogs = MutableStateFlow(listOf(Changelog()))
    private var _currentChangelog = MutableStateFlow(Changelog())

    fun getCurrent(): Changelog {
        viewModelScope.launch {
            _currentChangelog.update {
                ChangelogGetter.getCurrent()
            }
        }
        return _currentChangelog.value
    }

    fun getAll(): List<Changelog> {
        viewModelScope.launch {
            _allChangelogs.update {
                ChangelogGetter.getAll()
            }
        }
        return _allChangelogs.value
    }
}
