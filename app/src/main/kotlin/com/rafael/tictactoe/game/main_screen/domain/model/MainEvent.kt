package com.rafael.tictactoe.game.mainScreen.domain.model

import com.rafael.tictactoe.game.domain.model.GameMode

sealed interface MainEvent {
    data class SetGameMode(val mode: GameMode = GameMode.PvP) : MainEvent
}
