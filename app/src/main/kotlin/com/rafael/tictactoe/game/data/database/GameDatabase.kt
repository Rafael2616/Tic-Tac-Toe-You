package com.rafael.tictactoe.game.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.data.dao.GameDao


@Database(
    entities = [GameEntity::class],
    version = 1,
    exportSchema = false
) 
abstract class GameDatabase : RoomDatabase() {
    
    abstract val gameDao: GameDao

    companion object {
        const val DATABASE_NAME = "game_database"
    }
}
