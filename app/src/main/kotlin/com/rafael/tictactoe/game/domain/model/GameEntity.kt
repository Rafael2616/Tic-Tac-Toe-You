package com.rafael.tictactoe.game.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey
    var currentPlayerName: String,
    var player1Name: String,
    var player2Name: String,
    var player1Wins: Int,
    var player2Wins: Int,
) {
    companion object {
        fun defaultGameValues(): GameEntity {
            return GameEntity(
                currentPlayerName = "",
                player1Name = "",
                player2Name = "",
                player1Wins = 0,
                player2Wins = 0,
            )
        }
    }
}
