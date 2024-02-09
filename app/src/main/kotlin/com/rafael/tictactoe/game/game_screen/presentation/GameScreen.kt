package com.rafael.tictactoe.game.gameScreen.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.game.domain.checkers.GotOldChecker
import com.rafael.tictactoe.game.domain.checkers.WinnerChecker
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.domain.restart.RestartButtonLogic
import com.rafael.tictactoe.game.domain.toast.ToastNameHandler
import com.rafael.tictactoe.game.gameScreen.presentation.components.buttons.PlayAgainButton
import com.rafael.tictactoe.game.gameScreen.presentation.components.buttons.RestartButton
import com.rafael.tictactoe.game.gameScreen.presentation.components.buttons.SoundAndMusicButtons
import com.rafael.tictactoe.game.gameScreen.presentation.components.buttons.VictoryCounterReseterButton
import com.rafael.tictactoe.game.gameScreen.presentation.components.dialogs.ExitGameDialog
import com.rafael.tictactoe.game.gameScreen.presentation.components.dialogs.GetPlayersPrefsDialog
import com.rafael.tictactoe.game.gameScreen.presentation.components.ui.DifficultyLevelIndicator
import com.rafael.tictactoe.game.gameScreen.presentation.components.ui.GameBoard
import com.rafael.tictactoe.game.gameScreen.presentation.components.ui.GameStateToast
import com.rafael.tictactoe.game.gameScreen.presentation.components.ui.PlayerSearchSurface
import com.rafael.tictactoe.game.gameScreen.presentation.components.ui.PlayersNamesOnCounter
import com.rafael.tictactoe.game.gameScreen.presentation.components.ui.VictoryCounter
import com.rafael.tictactoe.game.gameScreen.presentation.components.ui.WinnerLines
import com.rafael.tictactoe.game.gameScreen.presentation.viewmodel.GameViewModel
import com.rafael.tictactoe.game.mainScreen.presentation.viewmodel.MainViewModel
import com.rafael.tictactoe.settings.settingsScreen.presentation.components.ColorSelectionDialog
import com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodel.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
fun GameScreen(
    navController: NavController,
    settingsVM: SettingsViewModel = hiltViewModel(),
    viewModel: GameViewModel = hiltViewModel(),
    mainVM: MainViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val mainState by mainVM.state.collectAsState()
    val settingsState by settingsVM.state.collectAsState()
    val onEvent = viewModel::onEvent
    val onSettingsEvent = settingsVM::onEvent
    val showDialog = if (!state.play && state.showGetPrefsDialog) true else false

    onEvent(GameEvent.SetGameMode(mainState.gameMode))

    GameBoard(
        onEvent,
        state,
        settingsState,
    )
    SoundAndMusicButtons(
        onEvent,
        state,
        settingsState,
    )
    VictoryCounter(
        state,
        settingsState,
    )
    GameStateToast(
        state,
        settingsState,
    )
    PlayersNamesOnCounter(
        state,
        onEvent,
    )

    if (state.gameMode == GameMode.VsCPU) {
        DifficultyLevelIndicator(
            state,
            onEvent,
            settingsState,
        )
    }

    AnimatedVisibility(showDialog) {
        GetPlayersPrefsDialog(
            onEvent,
            onSettingsEvent,
            state,
            settingsState,
        )
    }

    AnimatedVisibility(state.showRestartButton) {
        RestartButton(
            onEvent,
            state,
            settingsState,
        )
    }

    AnimatedVisibility(state.isGameFinished) {
        PlayAgainButton(
            onEvent,
            settingsState,
        )
        AnimatedVisibility(state.showVictoryCounterReseter) {
            VictoryCounterReseterButton(onEvent)
        }
    }

    AnimatedVisibility(
        visible = state.hasWinner,
        enter = fadeIn() + scaleIn(),
        exit = fadeOut(),
    ) {
        WinnerLines(
            state,
            settingsState,
        )
    }

    AnimatedVisibility(settingsState.showColorSelectionDialog) {
        ColorSelectionDialog(
            onSettingsEvent,
            settingsState,
        )
    }

    AnimatedVisibility(state.showSearchForPlayersSurface) {
        PlayerSearchSurface(
            state,
            settingsState,
            onEvent,
        )
    }

    AnimatedVisibility(state.showExitGameDialog) {
        ExitGameDialog(
            navController,
            onEvent,
        )
    }

    // Handle when some business logic will be called
    if (state.play && !state.isGameFinished) {
        WinnerChecker(onEvent, state)
        GotOldChecker(onEvent, state)
    }

    if (state.play) {
        RestartButtonLogic(
            onEvent,
            state,
        )
    }

    ToastNameHandler(state, onEvent)

    BackHandler {
        onEvent(GameEvent.ToggleExitDialog)
    }
}
