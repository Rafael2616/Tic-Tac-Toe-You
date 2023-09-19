package com.rafael.tictactoe.game.domain.util

import android.content.Context
import com.rafael.tictactoe.game.domain.util.CardGridSound
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

fun RandomCardSorter(
    vm: GameViewModel,
    settingsVM: SettingsViewModel,
    context: Context
) {
    val isCardClicked = vm.isCardClicked.value
    var randomPosition = (0 until isCardClicked[0].size).random()
    var secRandomPosition = (0 until isCardClicked[1].size).random()
    val isCPU = vm.isCPU.value
    val isCPUPlayer1 = vm.isCPUPlayer1.value
    val isXImage = vm.isXImage.value
    val isXFor1 = vm.isXFor1.value
    val isGameFinished = vm.isGameFinished.value
    val isTimerOn = vm.isTimerOn.value

    if (isGameFinished) {
        return
    }

    if (isCardClicked.flatten().all { it }) {
        return
    }

    while (isCardClicked[randomPosition][secRandomPosition]) {
        randomPosition = (0 until isCardClicked[0].size).random()
        secRandomPosition = (0 until isCardClicked[1].size).random()
    }

    if (isCPU) {
        CoroutineScope(Dispatchers.IO).launch {
            if (isTimerOn) {
                delay(2000L)
            } else {
                delay(500L)
            }
            vm.updateIsXImage(!isXImage)
            vm.updateIsCPU(false)
            if (isCPUPlayer1) {
                vm.updateCard(randomPosition, secRandomPosition, isXFor1)
                vm.updateIsCardClicked(randomPosition, secRandomPosition, true)
                vm.updateIsPlayer1(false)           
            } else {
                vm.updateCard(randomPosition, secRandomPosition, !isXFor1)
                vm.updateIsCardClicked(randomPosition, secRandomPosition, true)
                vm.updateIsPlayer1(true)
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            if (isTimerOn) {
                delay(2300L)
            } else {
                delay(500L)
            }
            CardGridSound(context, vm, settingsVM)
        }
    }
}