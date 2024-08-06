package com.rafael.tictactoe.feature.libraryinfo.data.repository

import com.rafael.tictactoe.feature.libraryinfo.domain.model.OssLibrary
import kotlinx.coroutines.flow.StateFlow

expect class OssLibraryRepository() {
    val ossLibraries: StateFlow<List<OssLibrary>>
}
