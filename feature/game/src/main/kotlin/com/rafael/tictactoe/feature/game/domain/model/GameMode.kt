package com.rafael.tictactoe.feature.game.domain.model

sealed interface GameMode {
    data object PvP : GameMode
    data object VsCPU : GameMode
    data object Online : GameMode
}
