package com.rafael.tictactoe.game.domain.board

import com.rafael.tictactoe.game.domain.board.levels.easyLevelCPUMove
import com.rafael.tictactoe.game.domain.board.levels.hardLevelCPUMove
import com.rafael.tictactoe.game.domain.board.levels.mediumLevelCPUMove
import com.rafael.tictactoe.game.domain.model.CPUDifficultyLevel
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun cpuMoveLevelsHandler(
    vm: GameViewModel,
    state: GameState,
    onEvent: (GameEvent) -> Unit,
) {
    val isCardClicked = state.isCardClicked
    val isCPU = state.isCPU
    val isCPUPlayer1 = state.isCPUPlayer1
    val isXImage = state.isXImage
    val isGameFinished = state.isGameFinished
    val isTimerOn = state.isTimerOn
    val delayTime = if (isTimerOn) 2300L else 570L
    val level = state.cpuDifficultyLevel
    val easy = easyLevelCPUMove(state)
    val medium = mediumLevelCPUMove(state, onEvent)
    val hard = hardLevelCPUMove(state)

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
            vm.updateIsPlayer1(!isCPUPlayer1)

            when (level) {
                CPUDifficultyLevel.Easy -> {
                    vm.updateCard(easy.first, easy.second, isXImage)
                    vm.updateIsCardClicked(easy.first, easy.second, true)
                }
                CPUDifficultyLevel.Medium -> {
                    vm.updateCard(medium.first, medium.second, isXImage)
                    vm.updateIsCardClicked(medium.first, medium.second, true)
                }
                CPUDifficultyLevel.Hard -> {
                    vm.updateCard(hard.first, hard.second, isXImage)
                    vm.updateIsCardClicked(hard.first, hard.second, true)
                }
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            delay(delayTime)
            vm.execCardGridSound()
        }
    }
}
