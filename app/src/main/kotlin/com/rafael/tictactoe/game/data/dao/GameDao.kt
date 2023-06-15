package com.rafael.tictactoe.game.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.rafael.tictactoe.game.domain.model.GameEntity

@Dao
interface GameDao {

	@Query("SELECT * FROM GameEntity LIMIT 1")
	suspend fun getGameValues(): GameEntity

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun setGameValues(gameEntity: GameEntity)
}