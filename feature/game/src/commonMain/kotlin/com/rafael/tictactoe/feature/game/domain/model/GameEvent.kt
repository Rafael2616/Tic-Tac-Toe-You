package com.rafael.tictactoe.feature.game.domain.model

sealed interface GameEvent {
    data class SetGameMode(val mode: String) : GameEvent
    data class GetFirstPlayerName(val name: String) : GameEvent
    data class GetSecondPlayerName(val name: String) : GameEvent
    data object SwitchPlayerPositions : GameEvent
    data object StartGame : GameEvent
    data class OnCardClick(val rowIndex: Int, val cardIndex: Int) : GameEvent
    data object ResetVictoryCounter : GameEvent
    data object PlayAgain : GameEvent
    data object ExitGame : GameEvent
    data object ToggleBgMusicButton : GameEvent
    data object ToggleSoundEffectsButton : GameEvent
    data object ToggleExpandButton : GameEvent
    data object ToggleExitDialog : GameEvent
    data object ToggleIsXForPlayer1 : GameEvent
    data object DismissExitDialog : GameEvent
    data class SelectCPUDifficultyMode(val level: Level) : GameEvent
    data class UpdateRequestState(val state: RequestState) : GameEvent
    data object GetRemotePlayers : GameEvent
    data object PostRemotePlayer : GameEvent
    data object RemoveRemotePlayer : GameEvent
    data object SendInitialOnlineGameState : GameEvent
    data object GetOnlineGameState : GameEvent
    data object UpdateOnlineGameState : GameEvent
    data object EndOnlineMatch : GameEvent
    data object UpdateIsYouPlayer1 : GameEvent
    data object StartOnlineMatch : GameEvent
}
