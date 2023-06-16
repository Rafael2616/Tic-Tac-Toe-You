package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository
import javax.inject.Inject

// THIS COORDINATES ONLY WORKS AS EXPECTED IN DEVICES WITH HD SCREEN (1280/720) AND NEEDS REWORK!!

class WinnerLineCoordinates @Inject constructor(private val repository: GameRepository) {
    val gameDefaults = GameEntity.defaultGameValues()

    suspend fun setFirstHorizontalLinePositions() {
        val game = repository.getGameValues() ?: gameDefaults
        game.startX = 15f
        game.startY = 725f
        game.endX = 705f
        game.endY = 725f
        repository.setGameValues(game)
    }

    suspend fun setSecondHorizontalLinePositions() {
        val game = repository.getGameValues() ?: gameDefaults
        game.startX = 15f
        game.startY = 950f
        game.endX = 705f
        game.endY = 950f
        repository.setGameValues(game)
    }

    suspend fun setTertiaryHorizontalLinePositions() {
        val game = repository.getGameValues() ?: gameDefaults
        game.startX = 15f
        game.startY = 1170f
        game.endX = 705f
        game.endY = 1170f
        repository.setGameValues(game)
    }

    suspend fun setFirstVerticalLinePositions() {
        val game = repository.getGameValues() ?: gameDefaults
        game.startX = 125f
        game.startY = 610f
        game.endX = 125f
        game.endY = 1285f
        repository.setGameValues(game)
    }

    suspend fun setSecondVerticalLinePositions() {
        val game = repository.getGameValues() ?: gameDefaults
        game.startX = 362f
        game.startY = 610f
        game.endX = 362f
        game.endY = 1285f
        repository.setGameValues(game)
    }

    suspend fun setTertiaryVerticalLinePositions() {
        val game = repository.getGameValues() ?: gameDefaults
        game.startX = 595f
        game.startY = 610f
        game.endX = 595f
        game.endY = 1285f
        repository.setGameValues(game)
    }

    suspend fun setStartToEndTransversalLinePositions() {
        val game = repository.getGameValues() ?: gameDefaults
        game.startX = 15f
        game.startY = 620f
        game.endX = 705f
        game.endY = 1275f
        repository.setGameValues(game)
    }

    suspend fun setEndToStartTransversalLinePositions() {
        val game = repository.getGameValues() ?: gameDefaults
        game.startX = 705f
        game.startY = 620f
        game.endX = 15f
        game.endY = 1275f
        repository.setGameValues(game)
    }
}