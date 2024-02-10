package com.rafael.tictactoe.settings.settingsScreen.data.database

import androidx.annotation.Keep
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rafael.tictactoe.settings.settingsScreen.data.dao.SettingsDao
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEntity

@Keep
@Database(
    entities = [SettingsEntity::class],
    version = 2,
    exportSchema = false,
)
abstract class SettingsDatabase : RoomDatabase() {

    abstract fun settingsDao(): SettingsDao

    companion object {
        const val DATABASE_NAME = "settings"
    }
}

@Keep
class Migrations {
    val migration1To2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            val addAppVersion = "ALTER TABLE 'settings' ADD COLUMN 'appVersion' TEXT NOT NULL DEFAULT 'O'"
            val addSelectedWinnerLineColor = "ALTER TABLE 'settings' ADD COLUMN 'selectedWinnerLineColor' INTEGER NOT NULL DEFAULT 2"
            val addSelectedXColor = "ALTER TABLE 'settings' ADD COLUMN 'selectedXColor' INTEGER NOT NULL DEFAULT 1"
            val addSelectedCircleColor = "ALTER TABLE 'settings' ADD COLUMN 'selectedCircleColor' INTEGER NOT NULL DEFAULT 5"

            db.execSQL(addAppVersion)
            db.execSQL(addSelectedWinnerLineColor)
            db.execSQL(addSelectedXColor)
            db.execSQL(addSelectedCircleColor)
        }
    }
}
