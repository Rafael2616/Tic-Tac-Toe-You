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
import com.rafael.tictactoe.game.presentation.game_screen.components.PlayerNamesLayout
import com.rafael.tictactoe.game.presentation.game_screen.components.ResetVictoryCounterButton
import com.rafael.tictactoe.game.presentation.game_screen.components.RestartButton
import com.rafael.tictactoe.game.presentation.game_screen.components.SoundEffectsButton
import com.rafael.tictactoe.game.presentation.game_screen.components.VictoryCounter
import com.rafael.tictactoe.game.presentation.game_screen.components.WinnerLine
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.util.CheckForWinner
import com.rafael.tictactoe.game.domain.util.CheckIfGotOld
import com.rafael.tictactoe.game.domain.util.GotOldSound
import com.rafael.tictactoe.game.domain.util.GameUtils
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.WinnerSound
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TestGameActivity : ComponentActivity() {

    @Inject lateinit var gameUtil: GameUtils
    @Inject lateinit var repository: GameRepository
    @Inject lateinit var settingsRepo: SettingsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(this) {
                testGameScreen()
            }
        }
    }

    @Composable
    fun testGameScreen() {
        val scope = rememberCoroutineScope()

        val viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        val settingsVM = ViewModelProvider(this).get(SettingsViewModel::class.java)
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

        Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxSize()
        ) {
			//BELOW THE LOGIC THAT DECIDE IN WHICH CASE THE FUNCTIONS MUST BE CALLED AND
            //PARAMS NEEEDED FOR THEM WORKS BECAUSE SOME FUNS NEEDS TO RECOMPOSE

            //WHEN THE ACTIVITY STARTS
            CardGrid(cards, isCardClicked, repository, gameUtil.coordinate)
            ExpandButton(this, repository, settingsRepo, expandButton)
            VictoryCounter(repository, player1Wins, player2Wins)
            PlayerNamesLayout(this, repository, gameUtil.playerName)  //THIS FUN CAUSES APP TO CRASH INTANTANEMENTE
            GameStateToast(this, repository, winner, gotOld)  //THIS FUN CAUSES APP TO CRASH INTANTANEMENTE

            if (!play) {
				GetPlayerPrefsCard(this, settingsRepo, repository,viewModel, isXFor1, player1Name, player2Name, isPlayer1, player1InToast)
			}
            if (play) {
                scope.launch {
                    CheckForWinner(repository)
                    CheckIfGotOld(repository)
                }
            }
            if (expandButton) {
                BackgroundMusicButton(this, isBackgroundMusicOn, settingsRepo)
                SoundEffectsButton(this, isSoundEffectsOn, settingsRepo)
            }
            if (showRestartButton) {
				RestartButton(this, repository, settingsRepo)
			}
            if (winner) {
                scope.launch {
                    WinnerSound(this@TestGameActivity, repository, settingsRepo)
                }
                WinnerLine(repository, gameUtil.coordinate, startX, startY, endX, endY)
                ResetVictoryCounterButton(this, repository, settingsRepo)
                PlayAgainButton(this, repository, settingsRepo)
            }
            if (gotOld) {
                scope.launch {
                    GotOldSound(this@TestGameActivity, repository, settingsRepo)
                }
                ResetVictoryCounterButton(this, repository, settingsRepo)
                PlayAgainButton(this, repository, settingsRepo)
            }
        }
    }
}