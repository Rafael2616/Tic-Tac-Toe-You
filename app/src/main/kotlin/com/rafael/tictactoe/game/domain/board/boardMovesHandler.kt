package com.rafael.tictactoe.game.domain.card_grid

import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun boardMovesHandler(
    state: GameState,
    onEvent: (GameEvent) -> Unit,
	vm: GameViewModel,
	rowIndex: Int,
	cardIndex: Int
) {
	val play = state.play
    val isCardClicked = state.isCardClicked
    val isXImage = state.isXImage
    val isPlayer1 = state.isPlayer1
    val isCPUPlayer1 = state.isCPUPlayer1
    val isTimerOn = state.isTimerOn
    val isGameFinished = state.isGameFinished
    val gameMode = state.gameMode

    if (isTimerOn) {
        return
    }

    if (isGameFinished) {
        return
    }

    if (isCardClicked[rowIndex][cardIndex]) {
        return
    }

    if (!play) {
        return
    }

	when (gameMode) {
        GameMode.PvP -> {
            vm.updateIsXImage(!isXImage)
     	   vm.updateCard(rowIndex, cardIndex, isXImage)
	    	vm.updateIsCardClicked(rowIndex, cardIndex, true)
	    	vm.updateIsPlayer1(!isPlayer1)
        }
        GameMode.VsCPU -> {
            // Exec human player move when needed 
            vm.updateIsXImage(!isXImage)
    	    vm.updateCard(rowIndex, cardIndex, isXImage)
	    	vm.updateIsCardClicked(rowIndex, cardIndex, true)
            vm.updateIsPlayer1(!isPlayer1)

            // Calls CPU player's move when needed
            if ((isCPUPlayer1 && !isPlayer1) || (!isCPUPlayer1 && isPlayer1)) {
                vm.updateIsCPU(true)
                onEvent(GameEvent.ExecCPUMove)
            } else {
                vm.updateIsCPU(false)
            }
        }
    }
}