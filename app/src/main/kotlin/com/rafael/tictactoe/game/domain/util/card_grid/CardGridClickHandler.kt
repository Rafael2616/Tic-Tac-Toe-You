package com.rafael.tictactoe.game.domain.util

import android.content.Context
import com.rafael.tictactoe.game.domain.util.CardGridSound
import com.rafael.tictactoe.game.domain.util.RandomCardSorter
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

suspend fun CardGridClickHandler(
    context: Context,
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
 	       vm.updateCard(rowIndex, cardIndex, isXImage)
			vm.updateIsCardClicked(rowIndex, cardIndex, true)
            vm.updateIsXImage(!isXImage)
			vm.updateIsPlayer1(false)
        } else {
            vm.updateCard(rowIndex, cardIndex, isXImage)
            vm.updateIsCardClicked(rowIndex, cardIndex, true)
            vm.updateIsXImage(!isXImage)
            vm.updateIsPlayer1(true)
        }
        vm.execCardGridSound(context)
    }
       
    //Vs CPU Mode
    if (isCPUMode) {
        if (isPlayer1) {
            vm.updateIsXImage(!isXImage)
	        vm.updateCard(rowIndex, cardIndex, isXFor1)
			vm.updateIsCardClicked(rowIndex, cardIndex, true)
            vm.updateIsPlayer1(false)
            if (isCPUPlayer1) {
                vm.updateIsCPU(false)
            } else {
                vm.updateIsCPU(true)           
            }
            RandomCardSorter(vm, context)
        } else {
            vm.updateIsXImage(!isXImage)
	        vm.updateCard(rowIndex, cardIndex, !isXFor1)
			vm.updateIsCardClicked(rowIndex, cardIndex, true)
            vm.updateIsPlayer1(true)
            if (isCPUPlayer1) {
                vm.updateIsCPU(true)
            } else {
                vm.updateIsCPU(false)        
            }
            RandomCardSorter(vm, context)
        }
        vm.execCardGridSound(context)
     }
}