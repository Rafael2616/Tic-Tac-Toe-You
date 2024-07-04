package com.rafael.tictactoe.feature.game.domain.model

sealed interface CPUDifficultyLevel {
    data object Easy : CPUDifficultyLevel
    data object Medium : CPUDifficultyLevel
    data object Hard : CPUDifficultyLevel
}
