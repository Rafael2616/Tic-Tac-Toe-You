package com.rafael.tictactoe.game.domain.card_grid

import com.rafael.tictactoe.game.domain.audio.CardGridSound
import com.rafael.tictactoe.game.domain.card_grid.EasyModeCPUCardClick
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

suspend fun CardGridClickHandler(
	vm: GameViewModel,
	rowIndex: Int,
	cardIndex: Int
) {
	val play = vm.play.value
    val isCardClicked = vm.isCardClicked.value
    val isXImage = vm.isXImage.value
    val isPlayer1 = vm.isPlayer1.value
    val isCPUPlayer1 = vm.isCPUPlayer1.value
    val isTimerOn = vm.isTimerOn.value
    val isCPUMode = vm.isCPUMode.value
    val isXFor1 = vm.isXFor1.value
    val isGameFinished = vm.isGameFinished.value

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
        vm.execCardGridSound()
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
                EasyModeCPUCardClick(vm)
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
                EasyModeCPUCardClick(vm)
            } else {
                vm.updateIsCPU(false)
            }
        }
        vm.execCardGridSound()
     }
}