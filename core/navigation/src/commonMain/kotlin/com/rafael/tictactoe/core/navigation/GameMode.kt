package com.rafael.tictactoe.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface GameMode {
    @Serializable
    data object PvP : GameMode

    @Serializable
    data object VsCPU : GameMode

    @Serializable
    data object Online : GameMode
}
