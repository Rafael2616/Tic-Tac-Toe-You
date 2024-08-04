package com.rafael.tictactoe.feature.settings.data.database

import androidx.annotation.Keep
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rafael.tictactoe.feature.settings.data.dao.SettingsDao
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEntity

@Keep
@Database(
    entities = [SettingsEntity::class],
    version = 3,
    exportSchema = false,
)
abstract class SettingsDatabase : RoomDatabase() {

    abstract fun settingsDao(): SettingsDao

    companion object {
        const val DATABASE_NAME = "settings"
    }
}

@Keep
internal class Migrations {
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

    val migration2To3 = object : Migration(2, 3) {
        override fun migrate(db: SupportSQLiteDatabase) {
            val addShapeSelected = "ALTER TABLE 'settings' ADD COLUMN 'shapeSelected' INTEGER NOT NULL DEFAULT 1"
            val addBoardCardsCornerRadius = "ALTER TABLE 'settings' ADD COLUMN 'boardCardsCornerRadius' FLOAT NOT NULL DEFAULT 24.0"
            val addBoardCardsThickness = "ALTER TABLE 'settings' ADD COLUMN 'boardCardsThickness' FLOAT NOT NULL DEFAULT 2.0"
            val addBoardCardsElevation = "ALTER TABLE 'settings' ADD COLUMN 'boardCardsElevation' FLOAT NOT NULL DEFAULT 20.0"
            val addBoardCardsShadow = "ALTER TABLE 'settings' ADD COLUMN 'boardCardsShadow' FLOAT NOT NULL DEFAULT 0.0"
            val addBoardCardsBorderColor = "ALTER TABLE 'settings' ADD COLUMN 'boardCardsBorderColor' TEXT NOT NULL DEFAULT 'FFFFFFFF'"
            val addIsRGBEffectOn = "ALTER TABLE 'settings' ADD COLUMN 'isRGBEffectOn' BOOLEAN NOT NULL DEFAULT false"
            val addIsGradientOn = "ALTER TABLE 'settings' ADD COLUMN 'isGradientOn' BOOLEAN NOT NULL DEFAULT true"

            db.execSQL(addShapeSelected)
            db.execSQL(addBoardCardsCornerRadius)
            db.execSQL(addBoardCardsThickness)
            db.execSQL(addBoardCardsElevation)
            db.execSQL(addBoardCardsShadow)
            db.execSQL(addBoardCardsBorderColor)
            db.execSQL(addIsRGBEffectOn)
            db.execSQL(addIsGradientOn)
        }
    }
}
