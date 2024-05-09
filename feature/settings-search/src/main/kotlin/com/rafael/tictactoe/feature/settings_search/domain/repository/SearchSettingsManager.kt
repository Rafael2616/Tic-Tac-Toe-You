package com.rafael.tictactoe.feature.settingssearch.domain.repository

import com.rafael.tictactoe.feature.settings.domain.model.SearchItemSpecs

interface SearchSettingsManager {

    suspend fun init()

    suspend fun putSettings(settings: List<SearchItemSpecs>): Boolean

    suspend fun searchSettings(query: String): List<SearchItemSpecs>

    fun closeSession()
}
