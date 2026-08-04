package com.rafael.tictactoe.feature.game.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationEventHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.rafael.tictactoe.core.navigation.GameMode
import com.rafael.tictactoe.core.platform.Platform
import com.rafael.tictactoe.core.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.core.settings.domain.model.SettingsState
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
import com.rafael.tictactoe.feature.settings.presentation.components.dialogs.ColorSelectionDialog

@Composable
fun GameScreen(
    gameMode: GameMode,
    gameState: GameState,
    settingsState: SettingsState,
    onGameEvent: (GameEvent) -> Unit,
    onSettingsEvent: (SettingsEvent) -> Unit,
    onUpdateSettingsState: (SettingsState) -> Unit,
    popBackStack: () -> Unit,
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
            latestOnGameEvent,
            latestOnSettingsEvent,
            popBackStack,
        )
    }

    DisposableEffect(canSetMode) {
        latestOnGameEvent(GameEvent.SetGameMode(gameMode))
        canSetMode = false
        onDispose {}
    }

val navigationState = rememberNavigationEventState(
    currentInfo = NavigationEventInfo.None,
    backInfo = listOf(NavigationEventInfo.None)
)
    NavigationEventHandler(
        state = navigationState,
        isBackEnabled = !Platform.IS_WINDOWS
    ) {
        latestOnGameEvent(GameEvent.ToggleExitDialog)
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@PreviewScreenSizes
@Composable
private fun GameScreenPreview() {
    AppTheme {
        GameScreen(
            gameMode = GameMode.PvP,
            gameState = GameState(),
            settingsState = SettingsState(),
            onGameEvent = {},
            onSettingsEvent = {},
            onUpdateSettingsState = {},
            popBackStack = {},
        )
    }
}
