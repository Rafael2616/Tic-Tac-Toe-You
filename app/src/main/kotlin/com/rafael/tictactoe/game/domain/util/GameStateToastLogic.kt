package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import android.content.Context
import com.rafael.tictactoe.game.domain.repository.GameRepository


suspend fun GameStateToastLogic(repository: GameRepository, context: Context) {

    val winner = repository.getWinner()
    val player1Name = repository.getPlayer1Name()
    val player2Name = repository.getPlayer2Name()
    val isPlayer1 = repository.getIsPlayer1()
    val gotOld = repository.getGotOld()

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
        repository.setCurrentPlayerName(context.getString(R.string.got_old))
    }

    if (player1Name.isEmpty() && player2Name.isEmpty()) {
        repository.setCurrentPlayerName(context.getString(R.string.player))
    } else if (player1Name.isNotBlank() && player2Name.isNotBlank()) {
        repository.setCurrentPlayerName(playerName)
    } else if (player1Name.isEmpty() && player2Name.isNotBlank() && isPlayer1) {
        repository.setCurrentPlayerName(context.getString(R.string.player))
    } else if (player1Name.isEmpty() && player2Name.isNotBlank() && !isPlayer1) {
        repository.setCurrentPlayerName(player2Name)
    } else if (player1Name.isNotBlank() && player2Name.isEmpty() && isPlayer1) {
        repository.setCurrentPlayerName(player1Name)
    } else if (player1Name.isNotBlank() && player2Name.isEmpty() && !isPlayer1) {
        repository.setCurrentPlayerName(context.getString(R.string.player))
    } else {
        repository.setCurrentPlayerName("")
    }
}