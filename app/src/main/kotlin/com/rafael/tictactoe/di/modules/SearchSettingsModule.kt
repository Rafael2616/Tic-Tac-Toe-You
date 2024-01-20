package com.rafael.tictactoe.di

import android.app.Application
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.repository.SearchSettingsManager
import com.rafael.tictactoe.settings.searchSettingsScreen.presentation.viewmodel.SearchSettingsViewModel
import com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodel.SettingsViewModel
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
        searchManager: SearchSettingsManager,
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
