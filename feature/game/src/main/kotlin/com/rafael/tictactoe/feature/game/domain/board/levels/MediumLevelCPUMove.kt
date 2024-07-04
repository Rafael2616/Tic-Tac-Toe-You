package com.rafael.tictactoe.feature.game.domain.board.levels

import com.rafael.tictactoe.feature.game.domain.model.GameState

internal fun mediumLevelCPUMove(state: GameState): Triple<Int, Int, Boolean> {
    val easy = easyLevelCPUMove(state)
    val hard = hardLevelCPUMove(state)
    val isUsingEasyLevel = state.isUsingEasyLevel
    val usingEasyLevel: Boolean

    // Select which mode will be used in next cpu move
    val useEasy = (0 until 2).random()

    usingEasyLevel = useEasy == 0

    // Perform an move based in which difficulty level is current sorted
    return if (isUsingEasyLevel) {
        Triple(easy.first, easy.second, usingEasyLevel)
    } else {
        Triple(hard.first, hard.second, usingEasyLevel)
    }
}
