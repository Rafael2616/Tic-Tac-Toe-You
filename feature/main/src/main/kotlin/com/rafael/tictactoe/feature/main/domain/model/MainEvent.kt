package com.rafael.tictactoe.feature.main.domain.model

import com.rafael.tictactoe.feature.main.domain.model.GameMode

sealed interface MainEvent {
    data class SetGameMode(val mode: GameMode = GameMode.PvP) : MainEvent
}
