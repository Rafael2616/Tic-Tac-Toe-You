package com.rafael.tictactoe.game.presentation.game_screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.game.presentation.game_screen.components.buttons.BackgroundMusicButton
import com.rafael.tictactoe.game.presentation.game_screen.components.buttons.ExpandButton
import com.rafael.tictactoe.game.presentation.game_screen.components.buttons.PlayAgainButton
import com.rafael.tictactoe.game.presentation.game_screen.components.buttons.RestartButton
import com.rafael.tictactoe.game.presentation.game_screen.components.buttons.SoundEffectsButton
import com.rafael.tictactoe.game.presentation.game_screen.components.buttons.VictoryCounterReseterButton
import com.rafael.tictactoe.game.presentation.game_screen.components.dialogs.ExitGameDialog
import com.rafael.tictactoe.game.presentation.game_screen.components.dialogs.GetPlayersPrefsDialog
import com.rafael.tictactoe.game.presentation.game_screen.components.ui.CardGrid
import com.rafael.tictactoe.game.presentation.game_screen.components.ui.GameStateToast
import com.rafael.tictactoe.game.presentation.game_screen.components.ui.PlayersNamesLayout
import com.rafael.tictactoe.game.presentation.game_screen.components.ui.VictoryCounter
import com.rafael.tictactoe.game.presentation.game_screen.components.ui.WinnerLines
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.domain.util.GameUtils
import com.rafael.tictactoe.game.domain.util.GotOldChecker
import com.rafael.tictactoe.game.domain.util.RestartButtonLogic
import com.rafael.tictactoe.game.domain.util.ToastNameHandler
import com.rafael.tictactoe.game.domain.util.WinnerChecker
import com.rafael.tictactoe.settings.presentation.settings_screen.components.LineColorSelectorDialog
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
@ExperimentalComposeUiApi
fun GameScreen(
    gameUtil: GameUtils,
    navController: NavController,
    isCPUMode: Boolean
) {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val play by viewModel.play.collectAsState()
    val isExpandButtonShown by viewModel.isExpandButtonShown.collectAsState()
    val showRestartButton by viewModel.showRestartButton.collectAsState()
    val winner by viewModel.winner.collectAsState()
    val isGameFinished by viewModel.isGameFinished.collectAsState()
    val showExitGameDialog by viewModel.showExitGameDialog.collectAsState()
    val showLineColorSelectorDialog by settingsVM.showLineColorSelectorDialog.collectAsState()
    val playerName = gameUtil.playerName
    val context = LocalContext.current
    val update by rememberSaveable { mutableStateOf(true) }

	//Handle when each UI component function will be called

    //When GameScreen starts
    CardGrid()
    ExpandButton()
    VictoryCounter()
    PlayersNamesLayout()
    GameStateToast()
    ToastNameHandler()

    AnimatedVisibility(!play) {
		GetPlayersPrefsDialog()
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
        WinnerLines()
    }

    AnimatedVisibility(showLineColorSelectorDialog) {
        LineColorSelectorDialog()
    }

    AnimatedVisibility(showExitGameDialog) {
        ExitGameDialog(navController)
    }

    //Handle when some business logic will be called
    if (play) {
        WinnerChecker(viewModel, context)
        GotOldChecker(viewModel, context)
    	RestartButtonLogic(viewModel)
    }

    DisposableEffect(key1 = "update") {
        if (update) {
            viewModel.updateIsCPUMode(isCPUMode)
        }
        onDispose{}
    }

    BackHandler {
        viewModel.showExitGameDialog(!showExitGameDialog)  
    }
}