package com.rafael.tictactoe.game.domain.model

sealed interface CPUDifficultyLevel {
    object easy : CPUDifficultyLevel
    object hard : CPUDifficultyLevel
}