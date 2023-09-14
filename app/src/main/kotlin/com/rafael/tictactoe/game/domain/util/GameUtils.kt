package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.PlayersNamesDisplay

data class GameUtils(
    val coordinate: WinnerLineCoordinates,
    val playerName: PlayersNamesDisplay
)

//TODO//Implement others utils