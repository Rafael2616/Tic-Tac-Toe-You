package com.rafael.tictactoe.core.settings.di.modules

import com.rafael.tictactoe.core.settings.data.database.SettingsDatabase
import com.rafael.tictactoe.core.settings.data.database.getRoomDatabase
import com.rafael.tictactoe.core.settings.data.repository.SettingsRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

actual val coreSettingsModule: Module = module {
    single { SettingsRepositoryImpl(get<SettingsDatabase>().settingsDao()) }

    single {
        getRoomDatabase(
            klass = SettingsDatabase::class,
            databaseName = "settings.db",
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }
}
