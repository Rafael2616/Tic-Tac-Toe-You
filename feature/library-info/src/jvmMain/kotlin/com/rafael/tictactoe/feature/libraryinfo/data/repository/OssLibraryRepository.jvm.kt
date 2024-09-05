package com.rafael.tictactoe.feature.libraryinfo.data.repository

import com.rafael.tictactoe.feature.libraryinfo.domain.model.OssLibrary
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.json.Json

actual class OssLibraryRepository {

    private val kotlinxJson = Json {
        ignoreUnknownKeys = true
    }
    actual val ossLibraries: StateFlow<List<OssLibrary>> = flow {
        val librariesFile = loadJsonFile()
            ?.byteInputStream()
            ?.bufferedReader().use { it?.readText() }
        val ossLibraries = kotlinxJson.decodeFromString<List<OssLibrary>>(librariesFile ?: "[]")
            .asSequence()
            .filter { it.name != "unknown" }
            .distinctBy { "${it.groupId}:${it.artifactId}" }
            .sortedBy { it.name }
            .toList()
        emit(ossLibraries)
    }
        .flowOn(Dispatchers.IO)
        .stateIn(MainScope(), SharingStarted.Lazily, emptyList())

    private fun loadJsonFile(): String? {
        val resourcePath = "build/generated/dependencyAssets/jvm/artifacts.json"
        return try {
            val resource = File(resourcePath)
            resource.readText()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
