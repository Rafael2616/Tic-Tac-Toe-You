package com.rafael.tictactoe.game.domain.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.rafael.tictactoe.game.domain.model.CPUDifficultyLevel

sealed interface GameEvent {
    data class GetFirstPlayerName(val name: String) : GameEvent
    data class GetSecondPlayerName(val name: String) : GameEvent
    object SwitchPlayerPositions : GameEvent
    data class StartGame(val player1Value: String, val player2Value: String): GameEvent
    data class ClickOnCardGrid(val rowIndex: Int, val cardIndex: Int) : GameEvent
    object ExecCPUMove : GameEvent
    object ResetVictoryCounter : GameEvent
    object PlayAgain : GameEvent
    object ToggleBgMusicButton : GameEvent
    object ToggleSoundEffectsButton : GameEvent
    object ToggleExpandButton : GameEvent
    object ToggleExitDialog : GameEvent
    object ToggleIsXForPlayer1 : GameEvent
    object ExitGame : GameEvent
    object DimissExitDialog : GameEvent
    data class SelectCPUDifficultyMode(val level: CPUDifficultyLevel, val selectedLevel: Int, val icon: ImageVector, val name: Int) : GameEvent
    data class UpdateIsGotOldSoundPlaying(val value: Boolean) : GameEvent
    data class UpdateIsWinSoundPlaying(val value: Boolean) : GameEvent
}