package com.rafael.tictactoe.game.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.rafael.tictactoe.game.domain.model.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

	@Query("SELECT * FROM GameEntity")
	fun getGameValues(): Flow<GameEntity>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun setGameValues(gameEntity: GameEntity)
}