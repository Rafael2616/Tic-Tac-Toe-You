package com.rafael.tictactoe.core.navigation

sealed class Destinations(val screen: String) {
    data object Main : Destinations(screen = "main")
    data object Game : Destinations(screen = "game")
    data object Settings : Destinations(screen = "settings")
    data object Library : Destinations(screen = "library")
    data object SearchSettings : Destinations(screen = "searchSettings")
    data object Changelogs : Destinations(screen = "changelogs")
}
