package com.rafael.tictactoe.feature.settingssearch.di.modules

import com.rafael.tictactoe.feature.settingssearch.data.repository.AndroidSearchSettingsManager
import com.rafael.tictactoe.feature.settingssearch.domain.repository.SearchSettingsManager
import com.rafael.tictactoe.feature.settingssearch.presentation.viewmodel.SearchSettingsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual val searchSettingsModule: Module = module {
    single<SearchSettingsManager> {
        AndroidSearchSettingsManager(androidApplication())
    }
    viewModel {
        SearchSettingsViewModel(
            searchManager = get(),
            settingsVM = get(),
        )
    }
}
