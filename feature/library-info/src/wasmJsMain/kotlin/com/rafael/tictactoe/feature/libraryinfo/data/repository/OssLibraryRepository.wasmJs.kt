package com.rafael.tictactoe.feature.libraryinfo.data.repository

import com.rafael.tictactoe.feature.libraryinfo.domain.model.OssLibrary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.json.Json
import org.jetbrains.skiko.loadBytesFromPath

actual class OssLibraryRepository {

    private val kotlinxJson = Json {
        ignoreUnknownKeys = true
    }
    actual val ossLibraries: StateFlow<List<OssLibrary>> = flow {
        val librariesFile = loadJsonFile()
        val ossLibraries = kotlinxJson.decodeFromString<List<OssLibrary>>(librariesFile ?: "[]")
            .asSequence()
            .filter { it.name != "unknown" }
            .distinctBy { "${it.groupId}:${it.artifactId}" }
            .sortedBy { it.name }
            .toList()
        emit(ossLibraries)
    }
        .flowOn(Dispatchers.Main)
        .stateIn(MainScope(), SharingStarted.Lazily, emptyList())

    private suspend fun loadJsonFile(): String? {
        val filePath = "generated/dependencyAssets/wasmJs/artifacts.json"

        return try {
            loadBytesFromPath(filePath).decodeToString()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
