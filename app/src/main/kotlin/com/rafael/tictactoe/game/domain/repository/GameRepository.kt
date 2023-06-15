package com.rafael.tictactoe.game.domain.repository

import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.model.GameEntity

interface GameRepository {

    suspend fun setGameValues(gameEntity: GameEntity)
    suspend fun getGameValues(): GameEntity
}