package com.rafael.tictactoe

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.window.ComposeUIViewController
import com.rafael.tictactoe.core.navigation.impl.TicTacToeNavGraph

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
fun mainViewController() = ComposeUIViewController { TicTacToeNavGraph() }
