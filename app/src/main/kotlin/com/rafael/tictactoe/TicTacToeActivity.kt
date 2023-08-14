package com.rafael.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.common.presentation.navigation.NavGraph
import com.rafael.tictactoe.common.presentation.theme.AppTheme
import com.rafael.tictactoe.game.domain.util.GameUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class TicTacToeActivity : ComponentActivity() {

    @Inject
	lateinit var gameUtil: GameUtils
  
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme() {
                NavGraph(this, gameUtil)
            }
        }
    }
}