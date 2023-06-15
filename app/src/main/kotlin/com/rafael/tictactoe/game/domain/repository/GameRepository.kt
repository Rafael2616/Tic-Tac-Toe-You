package com.rafael.tictactoe.game.domain.repository

import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.model.GameEntity


interface GameRepository {

    suspend fun setGameValues(gameEntity: GameEntity)
    suspend fun getGameValues(): GameEntity

//The funs below will be removed soon
    suspend fun getCards(): List<String>
    suspend fun setCards(cards: List<Boolean>)

    suspend fun getIsCardClicked(): List<String>
    suspend fun setIsCardClicked(isCardClicked: List<Boolean>)

}