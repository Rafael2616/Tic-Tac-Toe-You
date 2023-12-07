package com.rafael.tictactoe.game.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.rafael.tictactoe.game.domain.model.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getGameValues(): Flow<GameEntity>

    @Upsert
    suspend fun setGameValues(gameEntity: GameEntity)
}
