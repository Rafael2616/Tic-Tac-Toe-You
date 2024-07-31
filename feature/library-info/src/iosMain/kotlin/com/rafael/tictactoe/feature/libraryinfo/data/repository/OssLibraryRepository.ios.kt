package com.rafael.tictactoe.feature.libraryinfo.data.repository

import com.rafael.tictactoe.feature.libraryinfo.domain.model.OssLibrary
import kotlinx.coroutines.flow.StateFlow

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class OssLibraryRepository {
    actual val ossLibraries: StateFlow<List<OssLibrary>>
        get() = TODO("OssLibraryRepository : Not yet implemented")
}
