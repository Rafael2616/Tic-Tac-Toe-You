package com.rafael.tictactoe.feature.main.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.rafael.tictactoe.feature.main.domain.model.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    fun canRedirectToAnGameModeScreen(can: Boolean) {
        _state.update {
            it.copy(canRedirectToAnGameModeScreen = can)
        }
    }
}
