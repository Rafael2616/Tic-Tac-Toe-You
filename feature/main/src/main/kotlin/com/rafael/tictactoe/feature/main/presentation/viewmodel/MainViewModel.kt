package com.rafael.tictactoe.feature.main.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rafael.tictactoe.feature.main.domain.model.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val savedState: SavedStateHandle,
) : ViewModel() {

    val state = savedState.getStateFlow("state", MainState())

    fun canRedirectToAnGameModeScreen(can: Boolean) {
        savedState["state"] = savedState.get<MainState>("state")?.copy(
            canRedirectToAnGameModeScreen = can,
        )
    }
}
