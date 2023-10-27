package com.rafael.tictactoe.game.domain.card_grid

import com.rafael.tictactoe.game.domain.audio.CardGridSound
import com.rafael.tictactoe.game.domain.model.CPUDifficultyLevel
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun CPUMoveLevelsHandler(
    vm: GameViewModel,
    state: GameState
) {
    val isCardClicked = state.isCardClicked
    val isCPU = state.isCPU
    val isCPUPlayer1 = state.isCPUPlayer1
    val isXImage = state.isXImage
    val isXFor1 = state.isXFor1
    val isGameFinished = state.isGameFinished
    val isTimerOn = state.isTimerOn
    val delayTime = if (isTimerOn) 2300L else 570L
    val level = state.cpuDifficultyLevel
    val easy = easyModeCPUMove(state)
    val hard = hardModeCPUMove(state)

    if (isGameFinished) {
        return
    }

    if (isCardClicked.flatten().all { it }) {
        return
    }

    if (isCPU) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(delayTime)
            vm.updateIsXImage(!isXImage)
            vm.updateIsCPU(false)
            if (isCPUPlayer1) {
                vm.updateIsPlayer1(false)
                
                when (level) {
                    CPUDifficultyLevel.easy -> {
                        vm.updateCard(easy.first, easy.second, isXFor1)
                        vm.updateIsCardClicked(easy.first, easy.second, true)
                    }
                    CPUDifficultyLevel.hard -> {
                        vm.updateCard(hard.first, hard.second, isXFor1)
                        vm.updateIsCardClicked(hard.first, hard.second, true)
                    }
                }
            } else {
                vm.updateIsPlayer1(true)
                when (level) {
                    CPUDifficultyLevel.easy -> {
                        vm.updateCard(easy.first, easy.second, !isXFor1)
                        vm.updateIsCardClicked(easy.first, easy.second, true)
                    }
                    CPUDifficultyLevel.hard -> {
                        vm.updateCard(hard.first, hard.second, !isXFor1)
                        vm.updateIsCardClicked(hard.first, hard.second, true)
                    }
                }
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            delay(delayTime)
            vm.execCardGridSound()
        }
    }
}