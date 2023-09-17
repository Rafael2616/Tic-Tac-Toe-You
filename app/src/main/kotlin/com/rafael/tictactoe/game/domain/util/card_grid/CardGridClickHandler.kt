package com.rafael.tictactoe.game.domain.util

import android.content.Context
import com.rafael.tictactoe.game.domain.util.CardGridSound
import com.rafael.tictactoe.game.domain.util.RandomCardSorter
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

suspend fun CardGridClickHandler(
    context: Context,
	vm: GameViewModel,
    settingsVM: SettingsViewModel,
	rowIndex: Int,
	cardIndex: Int
) {
	val play = vm.play.value
    val isCardClicked = vm.isCardClicked.value
    val isXImage = vm.isXImage.value
    val isPlayer1 = vm.isPlayer1.value
    val isTimerOn = vm.isTimerOn.value
    val isCPUMode = vm.isCPUMode.value
    val isGameFinished = vm.isGameFinished.value
    val isXFor1 = vm.isXFor1.value
    
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
        CardGridSound(context, vm, settingsVM)
    }
       
    //Vs CPU Mode
    if (isCPUMode) {
        if (isPlayer1) {
            vm.updateIsXImage(!isXImage)
	        vm.updateCard(rowIndex, cardIndex, isXFor1)
			vm.updateIsCardClicked(rowIndex, cardIndex, true)
            vm.updateIsPlayer1(false)
            vm.updateIsCPU(true)
            CardGridSound(context, vm, settingsVM)
            RandomCardSorter(vm, settingsVM, context)             
        }   
    }
}