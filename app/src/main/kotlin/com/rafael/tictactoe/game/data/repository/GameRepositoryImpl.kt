package com.rafael.tictactoe.game.data.repository

import com.rafael.tictactoe.game.data.dao.GameDao
import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GameRepositoryImpl @Inject constructor(
	private val gameDao: GameDao
) : GameRepository {

	init{
		println("Game repository has been initialized")
	}
	
	 override fun getGameValues(): Flow<GameEntity> {
        return gameDao.getGameValues()
    }
	
    override suspend fun setGameValues(gameEntity: GameEntity) {
        return gameDao.setGameValues(gameEntity)
    }
}