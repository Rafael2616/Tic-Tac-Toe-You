package com.rafael.tictactoe.game.data.repository

import com.rafael.tictactoe.game.data.dao.GameDao
import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
private val gameDao: GameDao
) : GameRepository {

    override suspend fun setGameValues(gameEntity: GameEntity) {
        return gameDao.updateGameValues(gameEntity)
    }

    override suspend fun getGameValues(): GameEntity {
        return gameDao.getGameValues()
    }

//The funs below will be removed soon
    override suspend fun getCards(): List<String> {
        return gameDao.getCards()
    }
    override suspend fun setCards(cards: List<Boolean>) {
        return gameDao.setCards(cards)
    }

    override suspend fun getIsCardClicked(): List<String> {
        return gameDao.getIsCardClicked()
    }
    override suspend fun setIsCardClicked(isCardClicked: List<Boolean>) {
        return gameDao.setIsCardClicked(isCardClicked)
    }

}