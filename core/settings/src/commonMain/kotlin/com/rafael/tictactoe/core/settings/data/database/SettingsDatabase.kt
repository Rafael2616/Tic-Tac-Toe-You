package com.rafael.tictactoe.core.settings.data.database

import com.rafael.tictactoe.core.settings.data.dao.SettingsDao

expect abstract class SettingsDatabase {

    abstract fun settingsDao(): SettingsDao

    companion object {
        val DATABASE_NAME: String
    }
}
