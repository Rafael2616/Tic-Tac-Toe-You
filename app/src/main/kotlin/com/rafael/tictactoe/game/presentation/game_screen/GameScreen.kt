package com.rafael.tictactoe.game.presentation.game_screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.rafael.tictactoe.game.presentation.game_screen.components.ui.DifficultyLevelIndicator
import com.rafael.tictactoe.game.presentation.game_screen.components.ui.GameStateToast
import com.rafael.tictactoe.game.presentation.game_screen.components.ui.PlayersNamesLayout
import com.rafael.tictactoe.game.presentation.game_screen.components.ui.VictoryCounter
import com.rafael.tictactoe.game.presentation.game_screen.components.ui.WinnerLines
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.domain.checkers.GotOldChecker
import com.rafael.tictactoe.game.domain.checkers.WinnerChecker
import com.rafael.tictactoe.game.domain.game_toast.ToastNameHandler
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.players_names.PlayersNamesDisplay
import com.rafael.tictactoe.game.domain.restart_game.RestartButtonLogic
import com.rafael.tictactoe.settings.presentation.settings_screen.components.ColorSelectionDialog
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun GameScreen(
    navController: NavController,
    isCPUMode: Boolean
) {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val playerName = PlayersNamesDisplay()
    val update by rememberSaveable { mutableStateOf(true) }
    val state by viewModel.state.collectAsState()
    val settingsState by settingsVM.state.collectAsState()

	//Handle when each UI component function will be called

    //When GameScreen starts
    CardGrid(
        viewModel::onEvent,
        state,
        settingsState
    )
    ExpandButton(viewModel::onEvent, state)
    VictoryCounter(state, settingsState)
    PlayersNamesLayout(state)
    GameStateToast(state, settingsState)
    ToastNameHandler(state)
    
    if (state.isCPUMode) {
        DifficultyLevelIndicator(state, settingsState)
    }

    AnimatedVisibility(!state.play) {
		GetPlayersPrefsDialog(
            viewModel::onEvent,
            settingsVM::onEvent,
            state,
            settingsState
        )
        playerName.firstPlayerName(state)
        playerName.secondPlayerName(state)
    }

    AnimatedVisibility(state.isExpandButtonShown) {
        BackgroundMusicButton(viewModel::onEvent, settingsState)
        SoundEffectsButton(viewModel::onEvent, settingsState)
    }

    AnimatedVisibility(state.showRestartButton) {
		RestartButton(
            viewModel::onEvent,
            state,
            settingsState
        )
	}

    AnimatedVisibility(state.isGameFinished) {
        PlayAgainButton(viewModel::onEvent, settingsState)
        AnimatedVisibility(state.showVictoryCounterReseter) {
            VictoryCounterReseterButton(viewModel::onEvent)
        }
    }

    AnimatedVisibility(
        visible = state.winner,
        enter = fadeIn() + scaleIn(),
        exit = fadeOut()
    ) {
        WinnerLines(state, settingsState)
    }

    AnimatedVisibility(settingsState.showColorSelectionDialog) {
        ColorSelectionDialog(settingsVM::onEvent, settingsState)
    }

    AnimatedVisibility(state.showExitGameDialog) {
        ExitGameDialog(navController, viewModel::onEvent)
    }

    //Handle when some business logic will be called
    if (state.play && !state.isGameFinished) {
        WinnerChecker(viewModel, state)
        GotOldChecker(viewModel, state)
    }

    if (state.play) {
    	RestartButtonLogic(viewModel, state)
    }

    DisposableEffect(key1 = "update") {
        if (update) {
            viewModel.updateIsCPUMode(isCPUMode)
        }
        onDispose{}
    }

    BackHandler {
        viewModel.onEvent(GameEvent.ToggleExitDialog)
    }
}