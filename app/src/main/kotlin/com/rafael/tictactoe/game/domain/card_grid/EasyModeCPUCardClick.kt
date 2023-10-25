package com.rafael.tictactoe.game.domain.card_grid

import com.rafael.tictactoe.game.domain.audio.CardGridSound
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun EasyModeCPUCardClick(
    vm: GameViewModel,
    state: GameState
) {
    val isCardClicked = state.isCardClicked
    var randomPosition = (0 until isCardClicked[0].size).random()
    var secRandomPosition = (0 until isCardClicked[1].size).random()
    val isCPU = state.isCPU
    val isCPUPlayer1 = state.isCPUPlayer1
    val isXImage = state.isXImage
    val isXFor1 = state.isXFor1
    val isGameFinished = state.isGameFinished
    val isTimerOn = state.isTimerOn
    val delayTime = if (isTimerOn) 2300L else 580L

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
            delay(delayTime)
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
            delay(delayTime)
            vm.execCardGridSound()
        }
    }
}