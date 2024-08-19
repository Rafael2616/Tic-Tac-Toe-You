package com.rafael.tictactoe.core.settings.di.modules

import androidx.room.Room
import com.rafael.tictactoe.core.settings.data.database.SettingsDatabase
import com.rafael.tictactoe.core.settings.data.repository.SettingsRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual val coreSettingsModule: Module = module {
    single {
        val dbFilePath = documentDirectory() + "/settings.db"
        Room.databaseBuilder<SettingsDatabase>(
            name = dbFilePath,
        )
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }
    single {
        SettingsRepositoryImpl(get<SettingsDatabase>().settingsDao())
    }
}

private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}
