package com.rafael.tictactoe.game.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update
import androidx.room.OnConflictStrategy
import com.rafael.tictactoe.game.domain.model.GameEntity


@Dao
interface GameDao {

@Update
suspend fun updateGameValues(gameEntity: GameEntity)

@Query("SELECT * FROM GameEntity LIMIT 1")
suspend fun getGameValues(): GameEntity

//The funs below will be removed soon
@Query("UPDATE GameEntity SET cards = :cards")
suspend fun setCards(cards: List<Boolean>)

@Query("UPDATE GameEntity SET isCardClicked = :isCardClicked")
suspend fun setIsCardClicked(isCardClicked: List<Boolean>)

@Query("SELECT cards FROM GameEntity")
suspend fun getCards(): List<String>

@Query("SELECT isCardClicked FROM GameEntity")
suspend fun getIsCardClicked(): List<String>

}