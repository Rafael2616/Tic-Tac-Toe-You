package com.rafael.tictactoe.di

import android.app.Application
import com.rafael.tictactoe.game.mainScreen.presentation.viewmodel.MainViewModel
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
    fun provideMainViewModel(): MainViewModel {
        return MainViewModel()
    }
}
