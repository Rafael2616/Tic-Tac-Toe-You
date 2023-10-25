package com.rafael.tictactoe.game.domain.model

sealed interface GameEvent {
    data class GetFirstPlayerName(val name: String) : GameEvent
    data class GetSecondPlayerName(val name: String) : GameEvent
    object SwitchPlayerPositions : GameEvent
    object StartGame : GameEvent
    data class ClickOnCardGrid(val rowIndex: Int, val cardIndex: Int) : GameEvent
    object ExecEasyModeCPUCardClick : GameEvent
    object ResetVictoryCounter : GameEvent
    object PlayAgain : GameEvent
    object ToggleBgMusicButton : GameEvent
    object ToggleSoundEffectsButton : GameEvent
    object ToggleExpandButton : GameEvent
    object ToggleExitDialog : GameEvent
    object ExitGame : GameEvent
    object DimissExitDialog : GameEvent
    data class UpdateIsGotOldSoundPlaying(val value: Boolean) : GameEvent
    data class UpdateIsWinSoundPlaying(val value: Boolean) : GameEvent
}