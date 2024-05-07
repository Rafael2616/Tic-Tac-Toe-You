package com.rafael.tictactoe.common.presentation.navigation

import android.content.Intent
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.LayoutDirection
import com.rafael.tictactoe.core.navigation.TicTacToeNavGraph

@Composable
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalTextApi
fun TicTacToeNavGraph(
    modifier: Modifier = Modifier,
    intent: Intent? = null,
) = TicTacToeNavGraph(modifier, intent)
