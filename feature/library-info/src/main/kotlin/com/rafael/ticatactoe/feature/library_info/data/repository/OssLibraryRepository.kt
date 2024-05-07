package com.rafael.tictactoe.settings.libraryScreen.data.repository

import android.app.Application
import com.rafael.tictactoe.settings.domain.model.OssLibrary
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.json.Json

class OssLibraryRepository @Inject constructor(
    private val application: Application,
) {

    private val kotlinxJson = Json {
        ignoreUnknownKeys = true
    }
    val ossLibraries: StateFlow<List<OssLibrary>> = flow {
        val librariesFile = application.resources.assets.open("artifacts.json")
            .bufferedReader().use { it.readText() }
        val ossLibraries = kotlinxJson.decodeFromString<List<OssLibrary>>(librariesFile)
            .asSequence()
            .filter { it.name != "unknown" }
            .distinctBy { "${it.groupId}:${it.artifactId}" }
            .sortedBy { it.name }
            .toList()
        emit(ossLibraries)
    }
        .flowOn(Dispatchers.IO)
        .stateIn(MainScope(), SharingStarted.Lazily, emptyList())
}
