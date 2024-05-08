package com.rafael.tictactoe.feature.main.domain.model

sealed interface GameMode {
    object PvP : GameMode
    object VsCPU : GameMode
    object Online : GameMode
}
