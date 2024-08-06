package com.rafael.tictactoe.feature.settingssearch.di.modules

import com.rafael.tictactoe.feature.settingssearch.presentation.viewmodel.SearchSettingsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual val searchSettingsModule: Module = module {
    viewModel {
        SearchSettingsViewModel()
    }
}
