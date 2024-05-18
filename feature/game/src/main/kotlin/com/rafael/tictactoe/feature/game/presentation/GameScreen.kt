package com.rafael.tictactoe.feature.game.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.rafael.tictactoe.feature.game.domain.model.GameEvent
import com.rafael.tictactoe.feature.game.domain.model.GameMode
import com.rafael.tictactoe.feature.game.presentation.components.buttons.PlayAgainButton
import com.rafael.tictactoe.feature.game.presentation.components.buttons.RestartButton
import com.rafael.tictactoe.feature.game.presentation.components.buttons.SoundAndMusicButtons
import com.rafael.tictactoe.feature.game.presentation.components.buttons.VictoryCounterReseterButton
import com.rafael.tictactoe.feature.game.presentation.components.dialogs.ExitGameDialog
import com.rafael.tictactoe.feature.game.presentation.components.dialogs.GetPlayersPrefsDialog
import com.rafael.tictactoe.feature.game.presentation.components.ui.DifficultyLevelSwitcher
import com.rafael.tictactoe.feature.game.presentation.components.ui.GameBoard
import com.rafael.tictactoe.feature.game.presentation.components.ui.GameStateToast
import com.rafael.tictactoe.feature.game.presentation.components.ui.PlayerSearchSurface
import com.rafael.tictactoe.feature.game.presentation.components.ui.VictoryCounter
import com.rafael.tictactoe.feature.game.presentation.components.ui.WinnerLines
import com.rafael.tictactoe.feature.game.presentation.viewmodel.GameViewModel
import com.rafael.tictactoe.feature.settings.presentation.components.dialogs.ColorSelectionDialog
import com.rafael.tictactoe.feature.settings.presentation.viewmodel.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
fun GameScreen(
    navController: NavController,
    gameMode: String,
    settingsVM: SettingsViewModel = hiltViewModel(),
    viewModel: GameViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val settingsState by settingsVM.state.collectAsStateWithLifecycle()
    val onEvent = viewModel::onEvent
    val onSettingsEvent = settingsVM::onEvent
    val showPlayerPrefsDialog = !state.play && state.showGetPrefsDialog
    var canSetMode by rememberSaveable { mutableStateOf(true) }

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

    AnimatedVisibility(state.showRestartButton) {
        RestartButton(
            onEvent,
            state,
            settingsState,
        )
    }

    if (state.gameMode == GameMode.VsCPU) {
        DifficultyLevelSwitcher(
            state,
            onEvent,
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

    AnimatedVisibility(showPlayerPrefsDialog) {
        GetPlayersPrefsDialog(
            onEvent,
            onSettingsEvent,
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

    DisposableEffect(canSetMode) {
        onEvent(GameEvent.SetGameMode(gameMode))
        canSetMode = false
        onDispose {}
    }

    BackHandler {
        onEvent(GameEvent.ToggleExitDialog)
    }
}
