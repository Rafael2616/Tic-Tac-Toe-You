package com.rafael.tictactoe.game.presentation.game_screen

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.domain.util.GameUtils
import com.rafael.tictactoe.game.domain.util.GotOldChecker
import com.rafael.tictactoe.game.domain.util.RestartButtonLogic
import com.rafael.tictactoe.game.domain.util.ToastNameHandler
import com.rafael.tictactoe.game.domain.util.WinnerChecker
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinatesTest
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    gameUtil: GameUtils,
    activity: Activity,
    isCPUMode: Boolean
) {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val card by viewModel.card.collectAsState()
    val isCardClicked by viewModel.isCardClicked.collectAsState()   
    val play by viewModel.play.collectAsState()
    val isExpandButtonShown by viewModel.isExpandButtonShown.collectAsState()
    val showRestartButton by viewModel.showRestartButton.collectAsState()
    val winner by viewModel.winner.collectAsState()
    val isGameFinished by viewModel.isGameFinished.collectAsState()
    val playerName = gameUtil.playerName
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    viewModel.updateIsCPUMode(isCPUMode)
    
	//Handle when each UI component function will be called

    //When GameScreen starts
    CardGrid()
    ExpandButton()
    VictoryCounter()
    PlayersNamesLayout()
    GameStateToast()
    ToastNameHandler()

    AnimatedVisibility(!play) {
		GetPlayerPrefsCard()
        playerName.firstPlayerName()
        playerName.secondPlayerName()
    }

    AnimatedVisibility(isExpandButtonShown) {
        BackgroundMusicButton()
        SoundEffectsButton()
    }

    AnimatedVisibility(showRestartButton) {
		RestartButton()
	}

    AnimatedVisibility(isGameFinished) {
        VictoryCounterReseterButton()
        PlayAgainButton()
    }

    AnimatedVisibility(winner) {
        WinnerLine(WinnerLineCoordinatesTest(activity))
    }

    //Conditions for some business logic funcions to be called
    if (play) {
    	RestartButtonLogic(viewModel)
        WinnerChecker(viewModel, settingsVM, context, card, isCardClicked)
        scope.launch {
            GotOldChecker(viewModel, settingsVM, context, isCardClicked)
        }
    }
}