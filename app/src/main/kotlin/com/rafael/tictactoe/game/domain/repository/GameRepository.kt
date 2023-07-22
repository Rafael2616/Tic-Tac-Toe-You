package com.rafael.tictactoe.game.domain.repository

import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.model.GameEntity
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    fun getGameValues(): Flow<GameEntity>
	
    suspend fun setGameValues(gameEntity: GameEntity)
}