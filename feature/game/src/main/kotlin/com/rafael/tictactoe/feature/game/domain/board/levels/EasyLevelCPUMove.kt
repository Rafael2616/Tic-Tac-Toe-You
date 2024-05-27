package com.rafael.tictactoe.feature.game.domain.board.levels

import com.rafael.tictactoe.feature.game.domain.model.GameState

internal fun easyLevelCPUMove(state: GameState): Pair<Int, Int> {
    val isCardClicked = state.isCardClicked
    var firstPosition = (0 until isCardClicked[0].size).random()
    var secondPosition = (0 until isCardClicked[1].size).random()

    if (!isCardClicked[firstPosition][secondPosition]) {
        return Pair(firstPosition, secondPosition)
    }

    if (isCardClicked.flatten().all { it }) {
        return Pair(-1, -1)
    }

    while (isCardClicked[firstPosition][secondPosition]) {
        firstPosition = (0 until isCardClicked[0].size).random()
        secondPosition = (0 until isCardClicked[1].size).random()

        if (!isCardClicked[firstPosition][secondPosition]) {
            return Pair(firstPosition, secondPosition)
        }
    }

    // Shoudn't reach here
    return Pair(-1, -1)
}
