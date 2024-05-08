package com.rafael.tictactoe.feature.main.di.modules

import androidx.lifecycle.SavedStateHandle
import com.rafael.tictactoe.feature.main.presentation.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideSavedStateHandle(): SavedStateHandle {
        return SavedStateHandle()
    }

    @Provides
    @Singleton
    fun provideMainViewModel(savedState: SavedStateHandle): MainViewModel {
        return MainViewModel(savedState)
    }
}
