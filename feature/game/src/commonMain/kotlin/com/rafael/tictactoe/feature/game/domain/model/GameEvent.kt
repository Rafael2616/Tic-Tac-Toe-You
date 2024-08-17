package com.rafael.tictactoe.feature.game.domain.model

import com.rafael.tictactoe.core.game.domain.model.GameMode
import com.rafael.tictactoe.core.platform.network.utils.Error
import com.rafael.tictactoe.core.platform.network.utils.Result
import com.rafael.tictactoe.core.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.game.domain.repository.ErrorMessage

sealed interface GameEvent {
    data class SetGameMode(val mode: GameMode) : GameEvent
    data class GetFirstPlayerName(val name: String) : GameEvent
    data class GetSecondPlayerName(val name: String) : GameEvent
    data object SwitchPlayerPositions : GameEvent
    data object StartGame : GameEvent
    data class OnCardClick(val rowIndex: Int, val cardIndex: Int) : GameEvent
    data object ResetVictoryCounter : GameEvent
    data object PlayAgain : GameEvent
    data class ExitGame(val onSettingsEvent: (SettingsEvent) -> Unit) : GameEvent
    data object ToggleExpandButton : GameEvent
    data object ToggleExitDialog : GameEvent
    data object ToggleIsXForPlayer1 : GameEvent
    data object DismissExitDialog : GameEvent
    data class SelectCPUDifficultyMode(val level: Level) : GameEvent
    data class UpdateRequestState(val state: Pair<Result<Any, Error>, ErrorMessage>? = null) :
        GameEvent
    data class GetRemotePlayers(val onSettingsEvent: ((SettingsEvent) -> Unit)? = null) : GameEvent
    data object PostRemotePlayer : GameEvent
    data object RemoveRemotePlayer : GameEvent
    data object SendInitialOnlineGameState : GameEvent
    data object GetOnlineGameState : GameEvent
    data object UpdateOnlineGameState : GameEvent
    data object EndOnlineMatch : GameEvent
    data object UpdateIsYouPlayer1 : GameEvent
    data object StartOnlineMatch : GameEvent
}
