package com.rafael.tictactoe.feature.settings.di.modules

import com.rafael.tictactoe.core.settings.data.repository.SettingsRepositoryImpl
import com.rafael.tictactoe.core.settings.di.modules.coreSettingsModule
import com.rafael.tictactoe.feature.settings.presentation.viewmodel.SettingsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingsModule: Module = module {
    includes(coreSettingsModule)
    viewModel {
        SettingsViewModel(get<SettingsRepositoryImpl>())
    }
}
