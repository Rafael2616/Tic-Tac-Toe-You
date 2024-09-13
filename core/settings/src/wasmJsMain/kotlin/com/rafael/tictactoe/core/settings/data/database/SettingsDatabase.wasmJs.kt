package com.rafael.tictactoe.core.settings.data.database

import com.rafael.tictactoe.core.settings.data.dao.SettingsDao

actual abstract class SettingsDatabase {
    actual abstract fun settingsDao(): SettingsDao

    actual companion object {
        actual val DATABASE_NAME: String = "settings"
    }
}
