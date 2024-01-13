package com.rafael.tictactoe.di

import android.app.Application
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.repository.SettingsSearchManager
import com.rafael.tictactoe.settings.searchSettingsScreen.presentation.viewmodels.SearchSettingsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchSettingsModule {

    @Provides
    @Singleton
    fun provideSearchSettingsViewModel(
        context: Application,
        searchManager: SettingsSearchManager,
    ): SearchSettingsViewModel {
        return SearchSettingsViewModel(
            context,
            searchManager,
        )
    }
}
