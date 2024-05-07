package com.rafael.tictactoe.game.domain.board

import com.rafael.tictactoe.game.domain.board.levels.easyLevelCPUMove
import com.rafael.tictactoe.game.domain.board.levels.hardLevelCPUMove
import com.rafael.tictactoe.game.domain.board.levels.mediumLevelCPUMove
import com.rafael.tictactoe.game.domain.model.CPUDifficultyLevel
import com.rafael.tictactoe.game.domain.model.GameState

fun cpuMovesHandler(state: GameState): MutableMap<String, Any>? {
    val isCardClicked = state.isCardClicked
    val isCPU = state.isCPU
    val isCPUPlayer1 = state.isCPUPlayer1
    val isXImage = state.isXImage
    val isGameFinished = state.isGameFinished
    val level = state.cpuDifficultyLevel
    var moveParams: MutableMap<String, Any> = mutableMapOf()

    if (isGameFinished) return null
    if (isCardClicked.flatten().all { it }) return null
    if (!isCPU) return null

    moveParams["isXImage"] = !isXImage
    moveParams["isCPU"] = false
    moveParams["isPlayer1"] = !isCPUPlayer1

    when (level) {
        CPUDifficultyLevel.Easy -> {
            val easy = easyLevelCPUMove(state)
            moveParams["card"] = listOf(easy.first, easy.second, isXImage)
            moveParams["isCardClicked"] = listOf(easy.first, easy.second, true)
        }
        CPUDifficultyLevel.Medium -> {
            val medium = mediumLevelCPUMove(state)
            moveParams["card"] = listOf(medium.first, medium.second, isXImage)
            moveParams["isCardClicked"] = listOf(medium.first, medium.second, true)
            moveParams["isUsingEasyLevel"] = medium.third
        }
        CPUDifficultyLevel.Hard -> {
            val hard = hardLevelCPUMove(state)
            moveParams["card"] = listOf(hard.first, hard.second, isXImage)
            moveParams["isCardClicked"] = listOf(hard.first, hard.second, true)
        }
    }
    return moveParams
}
