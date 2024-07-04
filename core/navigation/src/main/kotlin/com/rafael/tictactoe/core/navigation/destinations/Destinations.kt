package com.rafael.tictactoe.core.navigation.destinations

import kotlinx.serialization.Serializable

@Serializable
object Main

@Serializable
data class Game(
    val gameMode: String,
)

@Serializable
object Settings

@Serializable
object LibraryInfo

@Serializable
object SettingsSearch

@Serializable
object Changelogs
