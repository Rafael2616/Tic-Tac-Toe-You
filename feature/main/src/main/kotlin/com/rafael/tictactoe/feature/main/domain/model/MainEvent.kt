package com.rafael.tictactoe.feature.main.domain.model

sealed interface MainEvent {
    data class SetGameMode(val mode: GameMode = GameMode.PvP) : MainEvent
}
