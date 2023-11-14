package com.rafael.tictactoe.game.domain.model

import androidx.compose.ui.graphics.Color

data class Player(
    val id : Int,
    val name: String,
    val color : Color,
    val isXImage: Boolean
)