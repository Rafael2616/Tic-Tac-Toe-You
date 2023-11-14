package com.rafael.tictactoe.game.domain.repository

import com.rafael.tictactoe.game.domain.model.GameEntity
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    //Local data from Room
    fun getGameValues(): Flow<GameEntity>
    suspend fun setGameValues(gameEntity: GameEntity)
}