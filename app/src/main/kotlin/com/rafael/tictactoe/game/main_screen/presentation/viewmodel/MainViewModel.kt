package com.rafael.tictactoe.game.mainScreen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.rafael.tictactoe.game.mainScreen.domain.model.MainEvent
import com.rafael.tictactoe.game.mainScreen.domain.model.MainState
import com.rafael.tictactoe.game.domain.model.GameMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()
   
    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.SetGameMode -> {
                _state.update { it.copy(gameMode = event.mode) }
            }
        }
    }
}
