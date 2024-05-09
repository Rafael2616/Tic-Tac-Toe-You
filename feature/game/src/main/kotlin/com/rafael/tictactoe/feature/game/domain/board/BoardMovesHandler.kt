package com.rafael.tictactoe.feature.game.domain.board

import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.main.domain.model.GameMode

fun boardMovesHandler(
    state: GameState,
    rowIndex: Int,
    cardIndex: Int,
): MutableMap<String, Any>? {
    val play = state.play
    val isCardClicked = state.isCardClicked
    val isXImage = state.isXImage
    val isPlayer1 = state.isPlayer1
    val isCPUPlayer1 = state.isCPUPlayer1
    val isTimerOn = state.isTimerOn
    val isGameFinished = state.isGameFinished
    val gameMode = state.gameMode
    val isOnlinePlayerTurn = state.isOnlinePlayerTurn
    var moveParams: MutableMap<String, Any> = mutableMapOf()

    if (isTimerOn) return null
    if (isGameFinished) return null
    if (isCardClicked[rowIndex][cardIndex]) return null
    if (!play) return null

    when (gameMode) {
        GameMode.PvP -> {
            moveParams["isXImage"] = !isXImage
            moveParams["card"] = listOf(rowIndex, cardIndex, isXImage)
            moveParams["isCardClicked"] = listOf(rowIndex, cardIndex, true)
            moveParams["isPlayer1"] = !isPlayer1
        }
        GameMode.VsCPU -> {
            // Exec human player move when needed
            moveParams["isXImage"] = !isXImage
            moveParams["card"] = listOf(rowIndex, cardIndex, isXImage)
            moveParams["isCardClicked"] = listOf(rowIndex, cardIndex, true)
            moveParams["isPlayer1"] = !isPlayer1

            // Calls CPU player's move when needed
            if ((isCPUPlayer1 && !isPlayer1) || (!isCPUPlayer1 && isPlayer1)) {
                moveParams["isCPU"] = true
            } else {
                moveParams["isCPU"] = false
            }
        }
        GameMode.Online -> {
            if (isOnlinePlayerTurn) return null

            moveParams["isXImage"] = !isXImage
            moveParams["card"] = listOf(rowIndex, cardIndex, isXImage)
            moveParams["isCardClicked"] = listOf(rowIndex, cardIndex, true)
            moveParams["isPlayer1"] = !isPlayer1
            moveParams["isOnlinePlayerTurn"] = true
        }
    }
    return moveParams
}
