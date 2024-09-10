package com.rafael.tictactoe.core.navigation

import com.rafael.tictactoe.core.game.domain.model.GameMode
import kotlinx.serialization.Serializable

@Serializable
data object Main : Destinations

@Serializable
data class Game(
    val gameMode: GameMode,
) : Destinations

@Serializable
data object Settings : Destinations

@Serializable
data object LibraryInfo : Destinations

@Serializable
data object SettingsSearch : Destinations

@Serializable
data object Changelogs: Destinations

interface Destinations
