package com.rafael.tictactoe.core.settings.data.database

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
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
        val DATABASE_NAME: String = "settings"
    }
}
