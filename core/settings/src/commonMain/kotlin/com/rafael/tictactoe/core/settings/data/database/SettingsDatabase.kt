package com.rafael.tictactoe.core.settings.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.rafael.tictactoe.core.settings.data.dao.SettingsDao
import com.rafael.tictactoe.core.settings.domain.model.SettingsEntity

@Database(
    entities = [SettingsEntity::class],
    version = 4,
    exportSchema = false,
)
@ConstructedBy(SettingsDatabaseConstructor::class)
abstract class SettingsDatabase : RoomDatabase() {

    abstract fun settingsDao(): SettingsDao

    companion object {
        const val DATABASE_NAME = "settings"
    }
}
