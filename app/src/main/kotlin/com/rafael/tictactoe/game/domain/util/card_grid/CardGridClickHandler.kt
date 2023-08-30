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
	index: Int
) {
	val play = vm.play.value
    val isCardClicked = vm.isCardClicked.value
    val isXImage = vm.isXImage.value
    val isPlayer1 = vm.isPlayer1.value
    val isTimerOn = vm.isTimerOn.value
    val isCPUMode = vm.isCPUMode.value
    val isGameFinished = vm.isGameFinished.value

    if (isTimerOn) {
        return
    }

    if (isGameFinished) {
        return
    }

    if (isCardClicked[index]) {
        return
    }

    if (!play) {
        return
    }

	if (!isCPUMode) {
        if (isPlayer1) {
            vm.updateIsXImage(!isXImage)
			vm.updateIsPlayer1(false)
	        vm.updateCard(index, isXImage)
			vm.updateIsCardClicked(index, true)
        } else {
            vm.updateIsXImage(!isXImage)
            vm.updateIsPlayer1(true)      
            vm.updateCard(index, isXImage)
            vm.updateIsCardClicked(index, true)
        }
        CardGridSound(context, vm, settingsVM)
    }
   
    if (isCPUMode) {
        if (isPlayer1) {
            vm.updateIsXImage(!isXImage)
	        vm.updateCard(index, isXImage)
			vm.updateIsCardClicked(index, true)
            vm.updateIsPlayer1(false)
            vm.updateIsCPU(true)
            RandomCardSorter(vm, settingsVM, context)              
        }
        CardGridSound(context, vm, settingsVM)
    }
}