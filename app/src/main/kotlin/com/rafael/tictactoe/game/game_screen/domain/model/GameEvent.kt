package com.rafael.tictactoe.game.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

sealed interface GameEvent {
    data class SetGameMode(val mode: GameMode = GameMode.PvP) : GameEvent
    data class GetFirstPlayerName(val name: String) : GameEvent
    data class GetSecondPlayerName(val name: String) : GameEvent
    object SwitchPlayerPositions : GameEvent
    data class StartGame(val player1Value: String, val player2Value: String) : GameEvent
    data class ClickOnGameBoard(val rowIndex: Int, val cardIndex: Int) : GameEvent
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
    data class UseEasyLevel(val value: Boolean) : GameEvent
    data class SelectCPUDifficultyMode(val level: CPUDifficultyLevel, val selectedLevel: Int, val icon: ImageVector, val name: Int) : GameEvent
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
    data class ShowRestartButton(val value: Boolean) : GameEvent
    data class UpdateGotOld(val gotOld: Boolean) : GameEvent
    data class GameIsFinished(val value: Boolean) : GameEvent
    data class ShowVictoryCounterReseter(val value: Boolean) : GameEvent
    data class UpdateHasWinner(val hasWinner: Boolean) : GameEvent
    data class UpdateIs1Win(val value: Boolean) : GameEvent
    data class UpdateWinningPositionsIndices(val index: Int) : GameEvent
    data class UpdateHasLastWinningPlayer(val value: Boolean) : GameEvent
    object ResetWinningPositionsIndices : GameEvent
    data class UpdateIsWinSoundPlaying(val isPlaying: Boolean) : GameEvent
    data class UpdateIsGotOldSoundPlaying(val isPlaying: Boolean) : GameEvent
    data class UpdateIsScoreUp(val value: Boolean) : GameEvent
    data class UpdateAllIsCardClicked(val newCardClickedValues: List<List<Boolean>>) : GameEvent
    data class UpdateIsCPU(val value: Boolean) : GameEvent
    data class UpdateIsPlayer1(val value: Boolean) : GameEvent
    data class UpdateIsXImage(val value: Boolean) : GameEvent
    data class UpdateCard(val rowIndex: Int, val cardIndex: Int, val newValue: Boolean) : GameEvent
    data class UpdateIsCardClicked(val rowIndex: Int, val cardIndex: Int, val newValue: Boolean) : GameEvent
    data class IsOnlinePlayerTurn(val value: Boolean) : GameEvent
    data class UpdatePlayer1Wins(val value: Int) : GameEvent
    data class UpdatePlayer2Wins(val value: Int) : GameEvent
    data class UpdatePlayer1NameOnCounter(val name: String) : GameEvent
    data class UpdatePlayer2NameOnCounter(val name: String) : GameEvent
    data class UpdatePlayer1Name(val name: String) : GameEvent
    data class UpdatePlayer2Name(val name: String) : GameEvent
    data class UpdateCurrentPlayerName(val name: String) : GameEvent
    data class IsTimerOn(val value: Boolean) : GameEvent
    object ExecUIClickSound : GameEvent
}
