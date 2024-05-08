package com.rafael.tictactoe.feature.game.domain.board.levels

import com.rafael.tictactoe.feature.game.domain.model.GameState

fun mediumLevelCPUMove(state: GameState): Triple<Int, Int, Boolean> {
    val easy = easyLevelCPUMove(state)
    val hard = hardLevelCPUMove(state)
    val isUsingEasyLevel = state.isUsingEasyLevel
    var usingEasyLevel: Boolean

    // Select which mode will be used in next cpu move
    val useEasy = (0 until 2).random()

    usingEasyLevel = useEasy == 0

    // Perform an move based in which difficulty level is current sorted
    if (isUsingEasyLevel) {
        return Triple(easy.first, easy.second, usingEasyLevel)
    } else {
        return Triple(hard.first, hard.second, usingEasyLevel)
    }
}
