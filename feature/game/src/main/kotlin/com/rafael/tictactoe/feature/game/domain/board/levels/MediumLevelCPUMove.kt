package com.rafael.tictactoe.feature.game.domain.board.levels

import com.rafael.tictactoe.feature.game.domain.model.GameState

internal fun mediumLevelCPUMove(state: GameState): Triple<Int, Int, Boolean> {
    private val easy = easyLevelCPUMove(state)
    private val hard = hardLevelCPUMove(state)
    private val isUsingEasyLevel = state.isUsingEasyLevel
    var usingEasyLevel: Boolean

    // Select which mode will be used in next cpu move
    private val useEasy = (0 until 2).random()

    usingEasyLevel = useEasy == 0

    // Perform an move based in which difficulty level is current sorted
    if (isUsingEasyLevel) {
        return Triple(easy.first, easy.second, usingEasyLevel)
    } else {
        return Triple(hard.first, hard.second, usingEasyLevel)
    }
}
