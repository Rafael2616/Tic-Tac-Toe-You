package com.rafael.tictactoe.settings.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rafael.tictactoe.settings.data.dao.SettingsDao
import com.rafael.tictactoe.settings.domain.model.SettingsEntity

@Database(
    entities = [SettingsEntity::class],
    version = 1,
    exportSchema = false
) 
abstract class SettingsDatabase : RoomDatabase() {
    abstract val settingsDao: SettingsDao

    companion object {
        const val DATABASE_NAME = "settings_database"
    }
}