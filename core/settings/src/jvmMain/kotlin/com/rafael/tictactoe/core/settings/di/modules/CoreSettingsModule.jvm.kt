package com.rafael.tictactoe.core.settings.di.modules

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.rafael.tictactoe.core.settings.data.database.SettingsDatabase
import com.rafael.tictactoe.core.settings.data.repository.SettingsRepositoryImpl
import java.io.File
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

actual val coreSettingsModule: Module = module {
    val databaseDirectory = File(System.getProperty("java.io.tmpdir"), "settings.db")
    single {
        Room.databaseBuilder<SettingsDatabase>(
            databaseDirectory.absolutePath,
        )
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .fallbackToDestructiveMigration(true)
            .build()
    }
    single {
        SettingsRepositoryImpl(get<SettingsDatabase>().settingsDao())
    }
}
