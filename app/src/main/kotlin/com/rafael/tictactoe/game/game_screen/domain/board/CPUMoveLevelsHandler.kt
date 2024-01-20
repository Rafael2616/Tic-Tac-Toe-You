package com.rafael.tictactoe.game.domain.board

import com.rafael.tictactoe.game.domain.board.levels.easyLevelCPUMove
import com.rafael.tictactoe.game.domain.board.levels.hardLevelCPUMove
import com.rafael.tictactoe.game.domain.board.levels.mediumLevelCPUMove
import com.rafael.tictactoe.game.domain.model.CPUDifficultyLevel
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun cpuMoveLevelsHandler(
    state: GameState,
    onEvent: (GameEvent) -> Unit,
    execCardGridSound: () -> Unit,
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

    if (isGameFinished) return
    if (isCardClicked.flatten().all { it }) return

    if (isCPU) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(delayTime)
            onEvent(GameEvent.UpdateIsXImage(!isXImage))
            onEvent(GameEvent.UpdateIsCPU(false))
            onEvent(GameEvent.UpdateIsPlayer1(!isCPUPlayer1))

            when (level) {
                CPUDifficultyLevel.Easy -> {
                    onEvent(GameEvent.UpdateCard(easy.first, easy.second, isXImage))
                    onEvent(GameEvent.UpdateIsCardClicked(easy.first, easy.second, true))
                }
                CPUDifficultyLevel.Medium -> {
                    onEvent(GameEvent.UpdateCard(medium.first, medium.second, isXImage))
                    onEvent(GameEvent.UpdateIsCardClicked(medium.first, medium.second, true))
                }
                CPUDifficultyLevel.Hard -> {
                    onEvent(GameEvent.UpdateCard(hard.first, hard.second, isXImage))
                    onEvent(GameEvent.UpdateIsCardClicked(hard.first, hard.second, true))
                }
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            delay(delayTime)
            execCardGridSound()
        }
    }
}
