package com.rafael.tictactoe.core.settings.di.modules

import com.rafael.tictactoe.core.settings.data.database.SettingsDatabase
import com.rafael.tictactoe.core.settings.data.database.getRoomDatabase
import com.rafael.tictactoe.core.settings.data.repository.SettingsRepositoryImpl
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual val coreSettingsModule: Module = module {
    single {
        val dbFilePath = documentDirectory() + "/settings.db"
        getRoomDatabase(
            klass = SettingsDatabase::class,
            databaseName = dbFilePath,
        )
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }
    single {
        SettingsRepositoryImpl(get<SettingsDatabase>().settingsDao())
    }
}

@OptIn(ExperimentalForeignApi::class)
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
