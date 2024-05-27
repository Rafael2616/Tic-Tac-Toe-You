package com.rafael.tictactoe.feature.game.domain.model

internal sealed interface GameMode {
    object PvP : GameMode
    object VsCPU : GameMode
    object Online : GameMode
}
