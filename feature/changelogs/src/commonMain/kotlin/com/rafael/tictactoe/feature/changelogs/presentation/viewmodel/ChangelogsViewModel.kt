package com.rafael.tictactoe.feature.changelogs.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafael.tictactoe.feature.changelogs.data.ChangelogGetter
import com.rafael.tictactoe.feature.changelogs.domain.Changelog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChangelogsViewModel : ViewModel() {
    private var allChangelogs = MutableStateFlow(listOf(Changelog()))
    private var currentChangelog = MutableStateFlow(Changelog())

    fun getCurrent(): Changelog {
        viewModelScope.launch(Dispatchers.IO) {
            currentChangelog.update {
                ChangelogGetter.getCurrent()
            }
        }
        return currentChangelog.value
    }

    fun getAll(): List<Changelog> {
        viewModelScope.launch(Dispatchers.IO) {
            allChangelogs.update {
                ChangelogGetter.getAll()
            }
        }
        return allChangelogs.value
    }
}
