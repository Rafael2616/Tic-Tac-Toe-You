package com.rafael.tictactoe.core.settings.di.modules

import androidx.sqlite.driver.web.WebWorkerSQLiteDriver
import com.rafael.tictactoe.core.settings.data.database.SettingsDatabase
import com.rafael.tictactoe.core.settings.data.database.getRoomDatabase
import com.rafael.tictactoe.core.settings.data.repository.SettingsRepositoryImpl
import com.rafael.tictactoe.core.settings.domain.repository.SettingsRepository
import org.koin.core.module.Module
import org.koin.dsl.module
import org.w3c.dom.Worker

@OptIn(ExperimentalWasmJsInterop::class)
fun instantiateWorker(): Worker = js("new Worker(new URL('./sqlite-web-worker/worker.js', import.meta.url))")

actual val coreSettingsModule: Module = module {
    single {
        getRoomDatabase(
            klass = SettingsDatabase::class,
            databaseName = "settings.db",
        )
            .fallbackToDestructiveMigration(true)
            .setDriver(WebWorkerSQLiteDriver(instantiateWorker()))
            .build()
    }
    single<SettingsRepository> {
        SettingsRepositoryImpl(get<SettingsDatabase>().settingsDao())
    }
}
