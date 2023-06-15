package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import android.content.Context
import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository

suspend fun GameStateToastLogic(repository: GameRepository, context: Context) {

	val gameDefaults = GameEntity.defaultGameValues()
	val game = repository.getGameValues() ?: gameDefaults
    val winner = game.winner
    val player1Name = game.player1Name
    val player2Name = game.player2Name
    val isPlayer1 = game.isPlayer1
    val gotOld = game.gotOld

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
		game.currentPlayerName = context.getString(R.string.got_old)
		repository.setGameValues(game)
    }

    if (player1Name.isEmpty() && player2Name.isEmpty()) {
		game.currentPlayerName = context.getString(R.string.player)
		repository.setGameValues(game)
    } else if (player1Name.isNotBlank() && player2Name.isNotBlank()) {
		game.currentPlayerName = playerName
		repository.setGameValues(game)
    } else if (player1Name.isEmpty() && player2Name.isNotBlank() && isPlayer1) {
		game.currentPlayerName = context.getString(R.string.player)
		repository.setGameValues(game)
    } else if (player1Name.isEmpty() && player2Name.isNotBlank() && !isPlayer1) {
		game.currentPlayerName = player2Name
		repository.setGameValues(game)
    } else if (player1Name.isNotBlank() && player2Name.isEmpty() && isPlayer1) {
		game.currentPlayerName = player1Name
		repository.setGameValues(game)
    } else if (player1Name.isNotBlank() && player2Name.isEmpty() && !isPlayer1) {
		game.currentPlayerName = context.getString(R.string.player)
		repository.setGameValues(game)
    } else {
		game.currentPlayerName = ""
		repository.setGameValues(game)
    }
}