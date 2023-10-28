package com.rafael.tictactoe.game.domain.card_grid

import com.rafael.tictactoe.game.domain.audio.CardGridSound
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun cardGridMovesHandler(
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
    val isCPUMode = state.isCPUMode
    val isXFor1 = state.isXFor1
    val isGameFinished = state.isGameFinished

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

    //PvP Mode
	if (!isCPUMode) {
        if (isPlayer1) {
            // Exec player 1's move
 	       vm.updateCard(rowIndex, cardIndex, isXImage)
			vm.updateIsCardClicked(rowIndex, cardIndex, true)
            vm.updateIsXImage(!isXImage)
			vm.updateIsPlayer1(false)
        } else {
            // Exec player 2's move
            vm.updateCard(rowIndex, cardIndex, isXImage)
            vm.updateIsCardClicked(rowIndex, cardIndex, true)
            vm.updateIsXImage(!isXImage)
            vm.updateIsPlayer1(true)
        }
    }

    //Vs CPU Mode
    if (isCPUMode) {
        if (isPlayer1) {
            // Exec player 1's move
	        vm.updateCard(rowIndex, cardIndex, isXFor1)
			vm.updateIsCardClicked(rowIndex, cardIndex, true)
            vm.updateIsXImage(!isXImage)
            vm.updateIsPlayer1(false)

            // Calls CPU player's move during player 1's click if needed
            if (isCPUPlayer1) {
                vm.updateIsCPU(false)
            } else {
                vm.updateIsCPU(true)
                onEvent(GameEvent.ExecCPUMove)
            }
        } else {
            // Exec player 2's move
	        vm.updateCard(rowIndex, cardIndex, !isXFor1)
			vm.updateIsCardClicked(rowIndex, cardIndex, true)
            vm.updateIsXImage(!isXImage)
            vm.updateIsPlayer1(true)

            // Calls CPU player's move during player 2's click if needed
            if (isCPUPlayer1) {
                vm.updateIsCPU(true)
                onEvent(GameEvent.ExecCPUMove)
            } else {
                vm.updateIsCPU(false)
            }
        }
    }
}