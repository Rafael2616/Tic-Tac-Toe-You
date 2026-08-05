package com.rafael.tictactoe.core.settings.di.modules

import com.rafael.tictactoe.core.settings.data.database.LegacyMigrations
import com.rafael.tictactoe.core.settings.data.database.SettingsDatabase
import com.rafael.tictactoe.core.settings.data.database.getRoomDatabase
import com.rafael.tictactoe.core.settings.data.repository.SettingsRepositoryImpl
import com.rafael.tictactoe.core.settings.domain.repository.SettingsRepository
import org.koin.core.module.Module
import org.koin.dsl.module

actual val coreSettingsModule: Module = module {
    single {
        getRoomDatabase(
            klass = SettingsDatabase::class,
            databaseName = "settings.db",
        )
            .addMigrations(
                LegacyMigrations.migration1To2,
                LegacyMigrations.migration2To3,
                LegacyMigrations.migration3To4,
            )
            .fallbackToDestructiveMigration(true)
            .build()
    }
    single<SettingsRepository> {
        SettingsRepositoryImpl(get<SettingsDatabase>().settingsDao())
    }
}
