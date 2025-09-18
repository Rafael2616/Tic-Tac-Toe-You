package com.rafael.tictactoe.feature.libraryinfo.data.repository

import com.rafael.tictactoe.feature.libraryinfo.domain.model.OssLibrary
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.Json
import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.stringWithContentsOfFile
import platform.Foundation.NSUTF8StringEncoding

actual class OssLibraryRepository {

    private val kotlinxJson = Json {
        ignoreUnknownKeys = true
    }

    @OptIn(ExperimentalForeignApi::class)
    actual val ossLibraries: StateFlow<List<OssLibrary>> = flow {
        val filePath = NSBundle.mainBundle.pathForResource("artifacts", "json")
        requireNotNull(filePath) { "artifacts.json not found in iOS bundle" }

        val content = NSString.stringWithContentsOfFile(
            filePath, NSUTF8StringEncoding, null
        )?.toString() ?: error("Unable to read artifacts.json")

        val ossLibraries = kotlinxJson.decodeFromString<List<OssLibrary>>(content)
            .asSequence()
            .filter { it.name != "unknown" }
            .distinctBy { "${it.groupId}:${it.artifactId}" }
            .sortedBy { it.name }
            .toList()

        emit(ossLibraries)
    }.flowOn(Dispatchers.Default)
        .stateIn(MainScope(), SharingStarted.Lazily, emptyList())
}
