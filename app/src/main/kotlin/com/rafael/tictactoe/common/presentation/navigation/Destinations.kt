package com.rafael.tictactoe.common.presentation.navigation

sealed class Destinations(val screen: String) {
    object Main : Destinations(screen = "main")
    object Game : Destinations(screen = "game")
    object Settings : Destinations(screen = "settings")
    object Library : Destinations(screen = "library")
    object SearchSettings : Destinations(screen = "searchSettings")
}
