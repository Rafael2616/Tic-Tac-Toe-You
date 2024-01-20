package com.rafael.tictactoe.game.mainScreen.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rafael.tictactoe.game.mainScreen.domain.model.MainEvent
import com.rafael.tictactoe.game.mainScreen.domain.model.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedState: SavedStateHandle,
) : ViewModel() {

    val state = savedState.getStateFlow("state", MainState())

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.SetGameMode -> {
                savedState["state"] = savedState.get<MainState>("state")?.copy(
                    gameMode = event.mode,
                )
            }
        }
    }
}
