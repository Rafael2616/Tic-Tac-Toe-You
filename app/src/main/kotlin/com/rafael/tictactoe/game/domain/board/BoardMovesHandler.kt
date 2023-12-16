package com.rafael.tictactoe.game.domain.board

import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.domain.model.GameState

fun boardMovesHandler(
    state: GameState,
    onEvent: (GameEvent) -> Unit,
    rowIndex: Int,
    cardIndex: Int,
) {
    val play = state.play
    val isCardClicked = state.isCardClicked
    val isXImage = state.isXImage
    val isPlayer1 = state.isPlayer1
    val isCPUPlayer1 = state.isCPUPlayer1
    val isTimerOn = state.isTimerOn
    val isGameFinished = state.isGameFinished
    val gameMode = state.gameMode
    val isOnlinePlayerTurn = state.isOnlinePlayerTurn

    if (isTimerOn) return
    if (isGameFinished) return
    if (isCardClicked[rowIndex][cardIndex]) return
    if (!play) return

    when (gameMode) {
        GameMode.PvP -> {
            onEvent(GameEvent.UpdateIsXImage(!isXImage))
            onEvent(GameEvent.UpdateCard(rowIndex, cardIndex, isXImage))
            onEvent(GameEvent.UpdateIsCardClicked(rowIndex, cardIndex, true))
            onEvent(GameEvent.UpdateIsPlayer1(!isPlayer1))
        }
        GameMode.VsCPU -> {
            // Exec human player move when needed
            onEvent(GameEvent.UpdateIsXImage(!isXImage))
            onEvent(GameEvent.UpdateCard(rowIndex, cardIndex, isXImage))
            onEvent(GameEvent.UpdateIsCardClicked(rowIndex, cardIndex, true))
            onEvent(GameEvent.UpdateIsPlayer1(!isPlayer1))

            // Calls CPU player's move when needed
            if ((isCPUPlayer1 && !isPlayer1) || (!isCPUPlayer1 && isPlayer1)) {
                onEvent(GameEvent.UpdateIsCPU(true))
                onEvent(GameEvent.ExecCPUMove)
            } else {
                onEvent(GameEvent.UpdateIsCPU(false))
            }
        }
        GameMode.Online -> {
            if (isOnlinePlayerTurn) return

            onEvent(GameEvent.UpdateIsXImage(!isXImage))
            onEvent(GameEvent.UpdateCard(rowIndex, cardIndex, isXImage))
            onEvent(GameEvent.UpdateIsCardClicked(rowIndex, cardIndex, true))
            onEvent(GameEvent.UpdateIsPlayer1(!isPlayer1))
            onEvent(GameEvent.IsOnlinePlayerTurn(true))
        }
    }
}
