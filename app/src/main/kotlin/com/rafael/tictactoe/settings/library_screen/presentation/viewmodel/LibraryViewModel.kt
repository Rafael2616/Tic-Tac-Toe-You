package com.rafael.tictactoe.settings.libraryScreen.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.rafael.tictactoe.settings.libraryScreen.data.repository.OssLibraryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val libraryRepository: OssLibraryRepository,
) : ViewModel() {

    val ossLibraries = libraryRepository.ossLibraries
}
