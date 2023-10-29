package com.rafael.tictactoe.game.domain.model

sealed interface GameMode {
    object PvP : GameMode
    object VsCPU : GameMode 
}