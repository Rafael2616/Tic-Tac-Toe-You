package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.DisplayPlayerNames

data class GameUtils(
val coordinate: WinnerLineCoordinates,
val playerName: DisplayPlayerNames
)

//TODO//Implement others utils