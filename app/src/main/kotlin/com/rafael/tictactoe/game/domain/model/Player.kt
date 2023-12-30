package com.rafael.tictactoe.game.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val id: Int,
    val name: String,
    val color: Int,
    val isXFor1: Boolean,
    val isPlayer1: Boolean,
)
