package com.rafael.tictactoe.core.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Main : Destinations

@Serializable
data class Game(
    val gameMode: String,
) : Destinations

@Serializable
data object Settings : Destinations

@Serializable
data object LibraryInfo : Destinations

@Serializable
data object SettingsSearch : Destinations

@Serializable
data class Changelogs(
    val screenName: String = "",
) : Destinations

interface Destinations
