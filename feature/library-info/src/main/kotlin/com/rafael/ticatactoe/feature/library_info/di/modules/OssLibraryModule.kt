package com.rafael.tictactoe.feature.libraryinfo.di.modules

import com.rafael.tictactoe.feature.libraryinfo.data.repository.OssLibraryRepository
import com.rafael.tictactoe.feature.libraryinfo.presentation.viewmodel.LibraryViewModel
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
