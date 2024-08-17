package com.rafael.tictactoe.feature.game.presentation

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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rafael.tictactoe.core.game.domain.model.GameMode
import com.rafael.tictactoe.core.platform.presentation.OnBack
import com.rafael.tictactoe.core.ui.theme.AppTheme
import com.rafael.tictactoe.feature.game.domain.model.GameEvent
import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.game.presentation.components.buttons.PlayAgainButton
import com.rafael.tictactoe.feature.game.presentation.components.buttons.RestartButton
import com.rafael.tictactoe.feature.game.presentation.components.buttons.SoundAndMusicButtons
import com.rafael.tictactoe.feature.game.presentation.components.buttons.VictoryCounterResetButton
import com.rafael.tictactoe.feature.game.presentation.components.dialogs.ExitGameDialog
import com.rafael.tictactoe.feature.game.presentation.components.dialogs.GetPlayersPrefsDialog
import com.rafael.tictactoe.feature.game.presentation.components.ui.DifficultyLevelSwitcher
import com.rafael.tictactoe.feature.game.presentation.components.ui.GameBoard
import com.rafael.tictactoe.feature.game.presentation.components.ui.GameStateToast
import com.rafael.tictactoe.feature.game.presentation.components.ui.PlayerSearchSurface
import com.rafael.tictactoe.feature.game.presentation.components.ui.VictoryCounter
import com.rafael.tictactoe.feature.game.presentation.components.ui.WinnerLines
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.settings.presentation.components.dialogs.ColorSelectionDialog
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun GameScreen(
    navController: NavController,
    gameMode: GameMode,
    gameState: GameState,
    settingsState: SettingsState,
    onGameEvent: (GameEvent) -> Unit,
    onSettingsEvent: (SettingsEvent) -> Unit,
    onUpdateSettingsState: (SettingsState) -> Unit,
) {
    val latestOnSettingsEvent by rememberUpdatedState(onSettingsEvent)
    val latestOnGameEvent by rememberUpdatedState(onGameEvent)
    val latestOnUpdateSettingsState by rememberUpdatedState(onUpdateSettingsState)
    val showPlayerPrefsDialog = !gameState.play && gameState.showGetPrefsDialog
    var canSetMode by rememberSaveable { mutableStateOf(true) }

    GameBoard(
        latestOnGameEvent,
        gameState,
        settingsState,
        latestOnUpdateSettingsState,
    )
    SoundAndMusicButtons(
        latestOnGameEvent,
        gameState,
        latestOnSettingsEvent,
        settingsState,
    )
    VictoryCounter(
        gameState,
        settingsState,
    )
    GameStateToast(
        gameState,
        settingsState,
    )

    AnimatedVisibility(gameState.showRestartButton) {
        RestartButton(
            latestOnGameEvent,
            gameState,
            settingsState,
        )
    }

    if (gameState.gameMode == GameMode.VsCPU) {
        DifficultyLevelSwitcher(
            gameState,
            latestOnGameEvent,
            settingsState,
        )
    }

    AnimatedVisibility(gameState.isGameFinished) {
        PlayAgainButton(
            latestOnGameEvent,
            settingsState,
        )
        AnimatedVisibility(gameState.showVictoryCounterReset) {
            VictoryCounterResetButton(latestOnGameEvent)
        }
    }

    AnimatedVisibility(
        visible = gameState.hasWinner,
        enter = fadeIn() + scaleIn(),
        exit = fadeOut(),
    ) {
        WinnerLines(
            gameState,
            settingsState,
        )
    }

    AnimatedVisibility(showPlayerPrefsDialog) {
        GetPlayersPrefsDialog(
            latestOnGameEvent,
            latestOnSettingsEvent,
            gameState,
            settingsState,
        )
    }

    AnimatedVisibility(settingsState.showColorSelectionDialog) {
        ColorSelectionDialog(
            latestOnSettingsEvent,
            settingsState,
        )
    }

    AnimatedVisibility(gameState.showSearchForPlayersSurface) {
        PlayerSearchSurface(
            gameState,
            settingsState,
            latestOnGameEvent,
            latestOnSettingsEvent,
        )
    }

    AnimatedVisibility(gameState.showExitGameDialog) {
        ExitGameDialog(
            navController,
            latestOnGameEvent,
            latestOnSettingsEvent,
        )
    }

    DisposableEffect(canSetMode) {
        latestOnGameEvent(GameEvent.SetGameMode(gameMode))
        canSetMode = false
        onDispose {}
    }

    OnBack {
        latestOnGameEvent(GameEvent.ToggleExitDialog)
    }
}

@Preview
// @PreviewLightDark
// @PreviewScreenSizes
@Composable
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
private fun GameScreenPreview() {
    AppTheme {
        GameScreen(
            navController = rememberNavController(),
            gameMode = GameMode.PvP,
            gameState = GameState(),
            settingsState = SettingsState(),
            onGameEvent = {},
            onSettingsEvent = {},
            onUpdateSettingsState = {},
        )
    }
}
