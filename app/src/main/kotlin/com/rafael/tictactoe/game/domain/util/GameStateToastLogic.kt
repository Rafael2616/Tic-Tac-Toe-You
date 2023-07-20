package com.rafael.tictactoe.game.domain.util

import android.content.Context
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun GameStateToastLogic(
context: Context,
vm: GameViewModel,
winner: Boolean,
isPlayer1: Boolean,
gotOld: Boolean,
player1Name: String, 
player2Name: String
) {

    val playerName = if (!winner) {
    if (isPlayer1) {
            player1Name
        } else {
            player2Name
        }
    } else {
        if (!isPlayer1) {
            player1Name
        } else {
            player2Name
        }
    }

    if (gotOld) {
		vm.updateCurrentPlayerName(context.getString(R.string.got_old))
    }

    if (player1Name.isEmpty() && player2Name.isEmpty()) {
		vm.updateCurrentPlayerName(context.getString(R.string.player))
    } else if (player1Name.isNotBlank() && player2Name.isNotBlank()) {
		vm.updateCurrentPlayerName(playerName)
    } else if (player1Name.isEmpty() && player2Name.isNotBlank() && isPlayer1) {
		vm.updateCurrentPlayerName(context.getString(R.string.player))
    } else if (player1Name.isEmpty() && player2Name.isNotBlank() && !isPlayer1) {
		vm.updateCurrentPlayerName(player2Name)
    } else if (player1Name.isNotBlank() && player2Name.isEmpty() && isPlayer1) {
		vm.updateCurrentPlayerName(player1Name)
    } else if (player1Name.isNotBlank() && player2Name.isEmpty() && !isPlayer1) {
		vm.updateCurrentPlayerName(context.getString(R.string.player))
    } else {
		vm.updateCurrentPlayerName("")
    }
}