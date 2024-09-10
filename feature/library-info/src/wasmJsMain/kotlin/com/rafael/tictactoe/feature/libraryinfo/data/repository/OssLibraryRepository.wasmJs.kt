package com.rafael.tictactoe.feature.libraryinfo.data.repository

import com.rafael.tictactoe.feature.libraryinfo.domain.model.OssLibrary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

actual class OssLibraryRepository actual constructor() {
    actual val ossLibraries: StateFlow<List<OssLibrary>> = MutableStateFlow(emptyList())
}
