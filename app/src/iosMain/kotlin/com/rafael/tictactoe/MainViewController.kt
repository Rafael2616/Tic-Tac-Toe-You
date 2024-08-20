package com.rafael.tictactoe

import androidx.compose.ui.window.ComposeUIViewController
import com.rafael.tictactoe.core.navigation.impl.TicTacToeNavGraph

fun mainViewController() = ComposeUIViewController { TicTacToeNavGraph() }
