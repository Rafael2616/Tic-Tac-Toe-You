package com.rafael.tictactoe.game.domain.model

sealed interface GameEvent {
    data class SetGameMode(val mode: GameMode = GameMode.PvP) : GameEvent
    data class GetFirstPlayerName(val name: String) : GameEvent
    data class GetSecondPlayerName(val name: String) : GameEvent
    object SwitchPlayerPositions : GameEvent
    object StartGame : GameEvent
    data class OnCardClick(val rowIndex: Int, val cardIndex: Int) : GameEvent
    object ResetVictoryCounter : GameEvent
    object PlayAgain : GameEvent
    object ToggleBgMusicButton : GameEvent
    object ToggleSoundEffectsButton : GameEvent
    object ToggleExpandButton : GameEvent
    object ToggleExitDialog : GameEvent
    object ToggleIsXForPlayer1 : GameEvent
    object ExitGame : GameEvent
    object DimissExitDialog : GameEvent
    data class SelectCPUDifficultyMode(val level: Level) : GameEvent
    data class UpdateRequestState(val state: RequestState) : GameEvent
    object GetRemotePlayers : GameEvent
    object PostRemotePlayer : GameEvent
    object RemoveRemotePlayer : GameEvent
    object SendInitialOnlineGameState : GameEvent
    object GetOnlineGameState : GameEvent
    object UpdateOnlineGameState : GameEvent
    object EndOnlineMatch : GameEvent
    object UpdateIsYouPlayer1 : GameEvent
    object StartOnlineMatch : GameEvent
    object ExecUIClickSound : GameEvent
}
