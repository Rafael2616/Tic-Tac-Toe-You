package com.rafael.tictactoe.feature.settingssearch.di.modules

import android.app.Application
import com.rafael.tictactoe.feature.settingssearch.data.repository.AndroidSearchSettingsManager
import com.rafael.tictactoe.feature.settingssearch.presentation.viewmodel.SearchSettingsViewModel
import com.rafael.tictactoe.feature.settings.presentation.viewmodel.SettingsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SearchSettingsModule {

    @Provides
    @ViewModelScoped
    fun provideSearchSettingsViewModel(
        searchManager: AndroidSearchSettingsManager,
        settingsVM: SettingsViewModel,
        context: Application,
    ): SearchSettingsViewModel {
        return SearchSettingsViewModel(
            searchManager,
            settingsVM,
            context,
        )
    }
}
