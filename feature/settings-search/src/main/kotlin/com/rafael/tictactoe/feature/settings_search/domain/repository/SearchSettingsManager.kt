package com.rafael.tictactoe.settings.searchSettingsScreen.domain.repository

import com.rafael.tictactoe.settings.searchSettingsScreen.domain.model.SearchItemSpecs

interface SearchSettingsManager {

    suspend fun init()

    suspend fun putSettings(settings: List<SearchItemSpecs>): Boolean
    
    suspend fun searchSettings(query: String): List<SearchItemSpecs>
    
    fun closeSession()
}

