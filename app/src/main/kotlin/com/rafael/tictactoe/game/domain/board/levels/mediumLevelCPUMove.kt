package com.rafael.tictactoe.game.domain.board.levels

import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameState

fun mediumLevelCPUMove(
    state: GameState,
    onEvent: (GameEvent) -> Unit,
): Pair<Int, Int> {
    val easy = easyLevelCPUMove(state)
    val hard = hardLevelCPUMove(state)
    val isUsingEasyLevel = state.isUsingEasyLevel

    // Select which mode will be used in next cpu move
    val useEasy = (0 until 2).random()

    if (useEasy == 0) {
        onEvent(GameEvent.UseEasyLevel(true))
    } else {
        onEvent(GameEvent.UseEasyLevel(false))
    }

    // Perform an move based in which difficulty level is current sorted
    if (isUsingEasyLevel) {
        return Pair(easy.first, easy.second)
    } else {
        return Pair(hard.first, hard.second)
    }
}
