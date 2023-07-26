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
import com.rafael.tictactoe.game.presentation.game_screen.components.BackgroundMusicButton
import com.rafael.tictactoe.game.presentation.game_screen.components.CardGrid
import com.rafael.tictactoe.game.presentation.game_screen.components.ExpandButton
import com.rafael.tictactoe.game.presentation.game_screen.components.GameStateToast
import com.rafael.tictactoe.game.presentation.game_screen.components.GetPlayerPrefsCard
import com.rafael.tictactoe.game.presentation.game_screen.components.PlayAgainButton
import com.rafael.tictactoe.game.presentation.game_screen.components.PlayersNamesLayout
import com.rafael.tictactoe.game.presentation.game_screen.components.RestartButton
import com.rafael.tictactoe.game.presentation.game_screen.components.SoundEffectsButton
import com.rafael.tictactoe.game.presentation.game_screen.components.VictoryCounter
import com.rafael.tictactoe.game.presentation.game_screen.components.VictoryCounterReseterButton
import com.rafael.tictactoe.game.presentation.game_screen.components.WinnerLine
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.util.WinnerChecker
import com.rafael.tictactoe.game.domain.util.GotOldChecker
import com.rafael.tictactoe.game.domain.util.GotOldSound
import com.rafael.tictactoe.game.domain.util.GameUtils
import com.rafael.tictactoe.game.domain.util.RestartButtonLogic
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.WinnerSound
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameActivity : ComponentActivity() {

    @Inject lateinit var gameUtil: GameUtils
	
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(this) {
                GameScreen()
            }
        }
    }

    @Composable
    fun GameScreen() {
	
	val settingsVM = ViewModelProvider(this).get(SettingsViewModel::class.java)
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
        val isSoundEffectsOn by settingsVM.isSoundEffectsOn.collectAsState()
        val isBackgroundMusicOn by settingsVM.isBackgroundMusicOn.collectAsState()
        val startX by viewModel.startX.collectAsState()
        val startY by viewModel.startY.collectAsState()
        val endX by viewModel.endX.collectAsState()
        val endY by viewModel.endY.collectAsState()
        val winner by viewModel.winner.collectAsState()
        val gotOld by viewModel.gotOld.collectAsState()
        val showPlayer1Name by viewModel.showPlayer1Name.collectAsState()
        val showPlayer2Name by viewModel.showPlayer2Name.collectAsState()

       Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxSize()
        ) {
	    //BELOW THE LOGIC THAT DECIDE IN WHICH CASE THE FUNCTIONS MUST BE CALLED AND
            //PARAMS NEEEDED FOR THEM WORKS AND UPDATE UI IF NEEDED

            //WHEN THE ACTIVITY STARTS
            CardGrid(this, viewModel, settingsVM, gameUtil.coordinate)
            ExpandButton(this, viewModel, isSoundEffectsOn, expandButton)
            VictoryCounter(viewModel, isScoreUp, winner, is1Win, player1Wins, player2Wins)
            PlayersNamesLayout(this, viewModel, gameUtil.playerName, showPlayer1Name, showPlayer2Name, play, isPlayer1, isXFor1, player1Name, player2Name, player1InToast)
            GameStateToast(this, viewModel, isXImage, winner, isPlayer1, gotOld, player1Name, player2Name, currentPlayerName, isXFor1)
            RestartButtonLogic(viewModel, isCardClicked, winner, gotOld)

            if (!play) {
		GetPlayerPrefsCard(this, viewModel, isSoundEffectsOn, isXFor1, player1Name, player2Name, isPlayer1, player1InToast, play)
	    }
            if (play) {
   	        GotOldChecker(viewModel, isCardClicked, winner)
                WinnerChecker(viewModel, cards, isCardClicked)
            }
            if (expandButton) {
                BackgroundMusicButton(this, isBackgroundMusicOn, isSoundEffectsOn, settingsVM)
                SoundEffectsButton(this, settingsVM, isSoundEffectsOn)
            }
            if (showRestartButton) {
		RestartButton(this, viewModel, isSoundEffectsOn)
	    }
            if (winner) {
                WinnerSound(this, viewModel, isSoundEffectsOn, isWinSoundPlaying)
                WinnerLine(viewModel, gameUtil.coordinate, cards, isCardClicked, startX, startY, endX, endY)
                VictoryCounterReseterButton(this, viewModel, isSoundEffectsOn)
                PlayAgainButton(this, viewModel, isSoundEffectsOn, isCardClicked, winner, gotOld)
            }
            if (gotOld) {
                GotOldSound(this, viewModel, isSoundEffectsOn, isGotOldSoundPlaying)					
                VictoryCounterReseterButton(this, viewModel, isSoundEffectsOn)
                PlayAgainButton(this, viewModel, isSoundEffectsOn, isCardClicked, winner, gotOld)
            }
        }
    }
}
