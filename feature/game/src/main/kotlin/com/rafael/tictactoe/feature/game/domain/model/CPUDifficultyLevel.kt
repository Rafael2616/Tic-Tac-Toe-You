package com.rafael.tictactoe.feature.game.domain.model

sealed interface CPUDifficultyLevel {
    object Easy : CPUDifficultyLevel
    object Medium : CPUDifficultyLevel
    object Hard : CPUDifficultyLevel
}
