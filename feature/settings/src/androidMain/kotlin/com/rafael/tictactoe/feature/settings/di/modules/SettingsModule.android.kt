package com.rafael.tictactoe.feature.settings.di.modules

import androidx.room.Room
import com.rafael.tictactoe.feature.settings.data.database.Migrations
import com.rafael.tictactoe.feature.settings.data.database.SettingsDatabase
import com.rafael.tictactoe.feature.settings.data.repository.SettingsRepositoryImpl
import com.rafael.tictactoe.feature.settings.presentation.viewmodel.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual val settingsModule: Module
    get() = module {
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
        viewModel {
            SettingsViewModel(get<SettingsRepositoryImpl>())
        }
    }
