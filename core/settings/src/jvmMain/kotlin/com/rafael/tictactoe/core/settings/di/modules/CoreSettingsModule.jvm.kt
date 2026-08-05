package com.rafael.tictactoe.core.settings.di.modules

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.rafael.tictactoe.core.settings.data.database.SettingsDatabase
import com.rafael.tictactoe.core.settings.data.database.getRoomDatabase
import com.rafael.tictactoe.core.settings.data.repository.SettingsRepositoryImpl
import com.rafael.tictactoe.core.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

actual val coreSettingsModule: Module = module {
    single {
        getRoomDatabase(
            klass = SettingsDatabase::class,
            databaseName = "settings.db",
        )
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .fallbackToDestructiveMigration(true)
            .build()
    }
    single<SettingsRepository> {
        SettingsRepositoryImpl(get<SettingsDatabase>().settingsDao())
    }
}
