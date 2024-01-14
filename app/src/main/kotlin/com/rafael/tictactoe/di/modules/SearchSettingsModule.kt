package com.rafael.tictactoe.di

import com.rafael.tictactoe.settings.searchSettingsScreen.domain.repository.SearchSettingsManager
import com.rafael.tictactoe.settings.searchSettingsScreen.presentation.viewmodels.SearchSettingsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object SearchSettingsModule {

    @Provides
    @ViewModelScoped
    fun provideSearchSettingsViewModel(
        searchManager: SearchSettingsManager,
    ): SearchSettingsViewModel {
        return SearchSettingsViewModel(
            searchManager,
        )
    }
}
