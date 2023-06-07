package com.rafael.tictactoe.game.presentation.game_screen.activities

import android.content.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.lifecycle.*
import com.rafael.tictactoe.common.presentation.ui.theme.AppTheme
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TestGameActivity : ComponentActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
            setContent{ AppTheme(this) { testGameScreen()
        } 
    }
}

@Composable
fun testGameScreen(){

val viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

val currentPlayerName by viewModel.currentPlayerName.collectAsState()
val player1Name by viewModel.player1Name.collectAsState()
val player2Name by viewModel.player2Name.collectAsState()
val player1Wins by viewModel.player1Wins.collectAsState()
val player2Wins by viewModel.player2Wins.collectAsState()

val cards by viewModel.cards.collectAsState()
val isCardClicked by viewModel.isCardClicked.collectAsState()

val play by viewModel.play.collectAsState()
val isXImage by viewModel.isXImage.collectAsState()
val is1Win by viewModel.is1Win.collectAsState()
val isXFor1 by viewModel.isXFor1.collectAsState()
val isPlayer1 by viewModel.isPlayer1.collectAsState()
val player1InToast by viewModel.player1InToast.collectAsState()

val isScoreUp by viewModel.isScoreUp.collectAsState()
val expandButton by viewModel.expandButton.collectAsState()
val showRestartButton by viewModel.showRestartButton.collectAsState()
val isWinSoundPlaying by viewModel.isWinSoundPlaying.collectAsState()
val isGotOldSoundPlaying by viewModel.isGotOldSoundPlaying.collectAsState()
val isSoundEffectsOn by viewModel.isSoundEffectsOn.collectAsState()
val isBackgroundMusicOn by viewModel.isBackgroundMusicOn.collectAsState()

val startX by viewModel.startX.collectAsState()
val startY by viewModel.startY.collectAsState()
val endX by viewModel.endX.collectAsState()
val endY by viewModel.endY.collectAsState()

val winner by viewModel.winner.collectAsState()
val gotOld by viewModel.gotOld.collectAsState()


Surface(
     color = MaterialTheme.colorScheme.surface,
     modifier = Modifier.fillMaxSize()
 ) {

//TODO// CALL FUNCTIONS HERE..
}


}
}