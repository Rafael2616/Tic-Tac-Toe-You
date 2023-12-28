package com.rafael.tictactoe.di

import com.rafael.tictactoe.settings.libraryScreen.data.repository.OssLibraryRepository
import com.rafael.tictactoe.settings.libraryScreen.presentation.viewmodels.LibraryViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OssLibraryModule {

    @Provides
    @Singleton
    fun provideLibraryViewModel(
        libraryRepository: OssLibraryRepository,
    ): LibraryViewModel {
        return LibraryViewModel(
            libraryRepository,
        )
    }
}
