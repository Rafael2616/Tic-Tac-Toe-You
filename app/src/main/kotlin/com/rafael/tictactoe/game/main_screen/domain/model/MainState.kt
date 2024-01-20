package com.rafael.tictactoe.game.mainScreen.domain.model

import com.rafael.tictactoe.game.domain.model.GameMode

data class MainState(
    val gameMode: GameMode = GameMode.PvP,
)
