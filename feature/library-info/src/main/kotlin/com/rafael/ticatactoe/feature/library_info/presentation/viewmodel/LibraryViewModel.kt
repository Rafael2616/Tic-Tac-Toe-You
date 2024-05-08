package com.rafael.tictactoe.feature.libraryinfo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.rafael.tictactoe.feature.libraryinfo.data.repository.OssLibraryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val libraryRepository: OssLibraryRepository,
) : ViewModel() {

    val ossLibraries = libraryRepository.ossLibraries
}
