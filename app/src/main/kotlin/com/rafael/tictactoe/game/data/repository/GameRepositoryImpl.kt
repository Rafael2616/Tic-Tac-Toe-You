package com.rafael.tictactoe.game.data.repository

import com.rafael.tictactoe.game.data.dao.GameDao
import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
private val gameDao: GameDao
) : GameRepository {

    override suspend fun setGameValues(gameEntity: GameEntity) {
        return gameDao.setGameValues(gameEntity)
    }

    override suspend fun getGameValues(): GameEntity {
        return gameDao.getGameValues()
    }
}