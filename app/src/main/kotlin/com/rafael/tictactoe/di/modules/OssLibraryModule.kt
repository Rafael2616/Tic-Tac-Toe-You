package com.rafael.tictactoe.di

import com.rafael.tictactoe.settings.libraryScreen.data.repository.OssLibraryRepository
import com.rafael.tictactoe.settings.libraryScreen.presentation.viewmodel.LibraryViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OssLibraryModule {

    @Provides
    @ViewModelScoped
    fun provideLibraryViewModel(
        libraryRepository: OssLibraryRepository,
    ): LibraryViewModel {
        return LibraryViewModel(
            libraryRepository,
        )
    }
}
