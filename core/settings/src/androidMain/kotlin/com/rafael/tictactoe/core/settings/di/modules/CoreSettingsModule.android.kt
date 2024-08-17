package com.rafael.tictactoe.core.settings.di.modules

import androidx.room.Room
import com.rafael.tictactoe.core.settings.data.database.Migrations
import com.rafael.tictactoe.core.settings.data.database.SettingsDatabase
import com.rafael.tictactoe.core.settings.data.repository.SettingsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val coreSettingsModule: Module = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            SettingsDatabase::class.java,
            SettingsDatabase.DATABASE_NAME,
        )
            .addMigrations(
                Migrations().migration1To2,
                Migrations().migration2To3,
                Migrations().migration3To4,
            )
            .fallbackToDestructiveMigration(true)
            .build()
    }
    single {
        SettingsRepositoryImpl(get<SettingsDatabase>().settingsDao())
    }
}
