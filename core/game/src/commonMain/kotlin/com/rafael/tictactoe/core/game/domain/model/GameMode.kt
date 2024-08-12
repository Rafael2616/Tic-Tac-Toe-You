package com.rafael.tictactoe.core.game.domain.model

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
