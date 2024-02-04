package com.rafael.tictactoe.game.gameScreen.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.game.domain.audio.cardGridSound
import com.rafael.tictactoe.game.domain.audio.gotOldSound
import com.rafael.tictactoe.game.domain.audio.winnerSound
import com.rafael.tictactoe.game.domain.board.boardMovesHandler
import com.rafael.tictactoe.game.domain.board.cpuMoveLevelsHandler
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.domain.model.OnlineGameState
import com.rafael.tictactoe.game.domain.model.Player
import com.rafael.tictactoe.game.domain.model.RequestState
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.restart.restartGame
import com.rafael.tictactoe.game.domain.victoryCounter.victoryCounterUpdater
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodel.SettingsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class GameViewModel @Inject constructor(
    private val player: ExoPlayer,
    private val settingsVM: SettingsViewModel,
    private val context: Application,
    private val repository: GameRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()
    var onlineGameState: Flow<OnlineGameState>? = null
    var searchPlayersJob: Job? = null

    fun onEvent(event: GameEvent) {
        val localPlayer1 = state.value.localPlayer1
        val isCPUMode = state.value.gameMode == GameMode.VsCPU
        val isCPUPlayer1 = state.value.isCPUPlayer1

        when (event) {
            is GameEvent.SetGameMode -> {
                _state.update {
                    it.copy(gameMode = event.mode)
                }
            }
            is GameEvent.GetFirstPlayerName -> {
                _state.update {
                    if (localPlayer1) {
                        it.copy(player1Name = event.name)
                    } else {
                        it.copy(player2Name = event.name)
                    }
                }
            }
            is GameEvent.GetSecondPlayerName -> {
                _state.update {
                    if (localPlayer1) {
                        it.copy(player2Name = event.name)
                    } else {
                        it.copy(player1Name = event.name)
                    }
                }
            }
            GameEvent.SwitchPlayerPositions -> {
                _state.update {
                    if (isCPUMode) {
                        it.copy(isCPUPlayer1 = !it.isCPUPlayer1)
                    } else {
                        it.copy(localPlayer1 = !it.localPlayer1)
                    }
                }
                settingsVM.execUIClickSound()
            }
            GameEvent.StartGame -> {
                val player1Name = if (state.value.localPlayer1) {
                    state.value.player1Name
                } else {
                    state.value.player2Name
                }
                val player2Name = if (state.value.localPlayer1) {
                    state.value.player2Name
                } else {
                    state.value.player1Name
                }
                _state.update {
                    it.copy(
                        isXImage = it.isXFor1,
                        showGetPrefsDialog = false,
                        player1Name = player1Name,
                        player2Name = player2Name,
                    )
                }
                if (state.value.gameMode != GameMode.Online) {
                    _state.update {
                        it.copy(
                            isTimerOn = true,
                            play = true,
                        )
                    }
                } else {
                    val randomicID = (1 until 999999).random()
                    _state.update {
                        it.copy(
                            showSearchForPlayersSurface = true,
                            matchID = randomicID,
                        )
                    }
                    this.onEvent(GameEvent.UpdateIsYouPlayer1)
                }
                if (isCPUMode && isCPUPlayer1) {
                    _state.update { it.copy(isCPU = true) }
                    cpuMoveLevelsHandler(
                        state.value,
                        this::onEvent,
                        this::execCardGridSound,
                    )
                }
                settingsVM.execUIClickSound()
            }
            is GameEvent.ClickOnGameBoard -> {
                _state.update { it.copy(isExpandButtonShown = false) }
                boardMovesHandler(
                    state.value,
                    this::onEvent,
                    event.rowIndex,
                    event.cardIndex,
                )
                execCardGridSound()
            }
            GameEvent.ExecCPUMove -> {
                cpuMoveLevelsHandler(
                    state.value,
                    this::onEvent,
                    this::execCardGridSound,
                )
            }
            GameEvent.ResetVictoryCounter -> {
                _state.update {
                    it.copy(
                        showVictoryCounterReseter = false,
                        player1Wins = 0,
                        player2Wins = 0,
                    )
                }
                settingsVM.execUIClickSound()
            }
            GameEvent.PlayAgain -> {
                _state.update { it.copy(isExpandButtonShown = false) }
                restartGame(
                    this::onEvent,
                    state.value,
                )
                if (isCPUMode) {
                    cpuMoveLevelsHandler(
                        state.value,
                        this::onEvent,
                        this::execCardGridSound,
                    )
                }
                settingsVM.execUIClickSound()
            }
            GameEvent.ToggleBgMusicButton -> {
                settingsVM.onEvent(SettingsEvent.ToggleIsBackgroundMusicOn)
                settingsVM.execUIClickSound()
            }
            GameEvent.ToggleSoundEffectsButton -> {
                settingsVM.onEvent(SettingsEvent.ToggleIsSoundEffectsOn)
                settingsVM.execUIClickSound()
            }
            GameEvent.ToggleExpandButton -> {
                _state.update { it.copy(isExpandButtonShown = !it.isExpandButtonShown) }
                settingsVM.execUIClickSound()
            }
            GameEvent.ExitGame -> {
                settingsVM.onEvent(SettingsEvent.ShowColorSelectionDialog(false))
                val cardState = state.value.card
                _state.update { GameState() }
                searchPlayersJob?.cancel()
                searchPlayersJob = null
                // Keep card variable state
                _state.update { it.copy(card = cardState) }
                settingsVM.execUIClickSound()
            }
            GameEvent.DimissExitDialog -> {
                _state.update { it.copy(showExitGameDialog = false) }
                settingsVM.execUIClickSound()
            }
            GameEvent.ToggleExitDialog -> {
                _state.update { it.copy(showExitGameDialog = !it.showExitGameDialog) }
            }
            GameEvent.ToggleIsXForPlayer1 -> {
                _state.update { it.copy(isXFor1 = !it.isXFor1) }
                settingsVM.execUIClickSound()
            }
            is GameEvent.SelectCPUDifficultyMode -> {
                _state.update {
                    it.copy(
                        cpuDifficultyLevel = event.level,
                        selectedDifficultyLevel = event.selectedLevel,
                        difficultyLevelName = event.name,
                        difficultyLevelIcon = event.icon,
                    )
                }
                settingsVM.execUIClickSound()
            }
            is GameEvent.UseEasyLevel -> {
                _state.update { it.copy(isUsingEasyLevel = event.value) }
            }
            GameEvent.GetRemotePlayers -> {
                searchPlayersJob?.cancel()
                searchPlayersJob = viewModelScope.launch(Dispatchers.IO) {
                    val (response, requestState) = repository.getRemotePlayers()
                    response?.let { players ->
                        val availablePlayers: Flow<List<Player>> = flow { emit(players) }
                        val possiblePlayers: List<Player> = availablePlayers.map {
                            it.filter { players ->
                                players.isPlayer1 == !state.value.isYouPlayer1
                            }
                        }.first()
                        val randomPlayer = (0..possiblePlayers.size - 1).random()
                        _state.update { it.copy(secondPlayer = possiblePlayers[randomPlayer]) }

                        val onlinePlayerName = state.value.secondPlayer!!.name
                        _state.update {
                            it.copy(
                                currentPlayerName = onlinePlayerName,
                                isXFor1 = !state.value.secondPlayer!!.isXFor1,
                            )
                            if (state.value.secondPlayer!!.isPlayer1) {
                                it.copy(
                                    player1Name = onlinePlayerName,
                                    player1NameOnCounter = onlinePlayerName,
                                )
                            } else {
                                it.copy(
                                    player2Name = onlinePlayerName,
                                    player2NameOnCounter = onlinePlayerName,
                                )
                            }
                        }
                        if (state.value.secondPlayer!!.isXFor1) {
                            settingsVM.onEvent(SettingsEvent.SetXColor(settingsVM.Color(state.value.secondPlayer!!.color)))
                        } else {
                            settingsVM.onEvent(SettingsEvent.SetCircleColor(settingsVM.Color(state.value.secondPlayer!!.color)))
                        }
                    }
                    _state.update { it.copy(requestState = requestState) }
                    this.cancel()
                }
            }
            GameEvent.PostRemotePlayer -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val requestState = repository.postPlayer(state.value, settingsVM.state.value)
                    _state.update { it.copy(requestState = requestState) }
                }
            }
            GameEvent.RemoveRemotePlayer -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val requestState = repository.removePlayer(state.value.matchID)
                    _state.update { it.copy(requestState = requestState) }
                }
            }
            GameEvent.SendInitialOnlineGameState -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val requestState = repository.postInitialGameState(state.value.matchID)
                    _state.update { it.copy(requestState = requestState) }
                }
            }
            GameEvent.GetOnlineGameState -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val (response, requestState) = repository.getGameState()
                    response?.let {
                        onlineGameState = response
                        _state.update {
                            it.copy(
                                requestState = RequestState.Success,
                             /* player1Wins = onlineGameState.player1Wins.value,
                                player2Wins = onlineGameState.player2Wins.value,
                                card = onlineGameState.card.value,
                                isCardClicked = onlineGameState.isCardClicked.value,
                                play = onlineGameState.play.value,
                                isPlayer1 = onlineGameState.isPlayer1.value,
                                hasWinner = onlineGameState.hasWinner.value,
                                gotOld = onlineGameState.gotOld.value,
                                isGameFinished = onlineGameState.isGameFinished.value */
                            )
                        }
                    }
                    _state.update { it.copy(requestState = requestState) }
                }
            }
            GameEvent.UpdateOnlineGameState -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val (response, requestState) = repository.updateGameState(state.value)
                    _state.update { it.copy(requestState = requestState) }
                    onlineGameState = response!!
                }
            }
            GameEvent.EndOnlineMatch -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val requestState = repository.endMatch(state.value.matchID)
                    _state.update { it.copy(requestState = requestState) }
                }
            }
            is GameEvent.UpdateRequestState -> {
                _state.update { it.copy(requestState = event.state) }
            }
            GameEvent.UpdateIsYouPlayer1 -> {
                val isYouPlayer1 = (1..2).random()
                val value = if (isYouPlayer1 == 1) true else false

                _state.update {
                    it.copy(
                        isYouPlayer1 = value,
                        localPlayer1 = value,
                    )
                }
                this.onEvent(GameEvent.GetRemotePlayers)
            }
            GameEvent.StartOnlineMatch -> {
                _state.update {
                    it.copy(
                        play = true,
                        isTimerOn = true,
                        isPlayer1 = state.value.localPlayer1,
                        showSearchForPlayersSurface = false,
                    )
                }
            }
            is GameEvent.ShowRestartButton -> {
                _state.update { it.copy(showRestartButton = event.value) }
            }
            is GameEvent.UpdateGotOld -> {
                _state.update { it.copy(gotOld = event.gotOld) }
                if (event.gotOld && !state.value.hasWinner) {
                    execGotOldSound()
                }
            }
            is GameEvent.GameIsFinished -> {
                _state.update { it.copy(isGameFinished = event.value) }
                if (state.value.hasWinner) {
                    victoryCounterUpdater(state.value, this::onEvent)
                }
            }
            is GameEvent.ShowVictoryCounterReseter -> {
                _state.update { it.copy(showVictoryCounterReseter = event.value) }
            }
            is GameEvent.UpdateHasWinner -> {
                _state.update { it.copy(hasWinner = event.hasWinner) }
                if (event.hasWinner) {
                    execWinnerSound()
                }
            }
            is GameEvent.UpdateIs1Win -> {
                _state.update { it.copy(is1Win = event.value) }
            }
            is GameEvent.UpdateHasLastWinningPlayer -> {
                _state.update { it.copy(hasLastWinningPlayer = event.value) }
            }
            is GameEvent.UpdateWinningPositionsIndices -> {
                val currentIndices = state.value.winningPositionsIndices.toMutableList()
                currentIndices.add(event.index)
                _state.update { it.copy(winningPositionsIndices = currentIndices) }
            }
            GameEvent.ResetWinningPositionsIndices -> {
                _state.update { it.copy(winningPositionsIndices = emptyList()) }
            }
            is GameEvent.UpdateIsWinSoundPlaying -> {
                _state.update { it.copy(isWinSoundPlaying = event.isPlaying) }
            }
            is GameEvent.UpdateIsGotOldSoundPlaying -> {
                _state.update { it.copy(isGotOldSoundPlaying = event.isPlaying) }
            }
            is GameEvent.UpdateIsScoreUp -> {
                _state.update { it.copy(isScoreUp = event.value) }
            }
            is GameEvent.UpdateAllIsCardClicked -> {
                _state.update { it.copy(isCardClicked = event.newCardClickedValues) }
            }
            is GameEvent.UpdateIsCPU -> {
                _state.update { it.copy(isCPU = event.value) }
            }
            is GameEvent.UpdateIsPlayer1 -> {
                _state.update { it.copy(isPlayer1 = event.value) }
            }
            is GameEvent.UpdateIsXImage -> {
                _state.update { it.copy(isXImage = event.value) }
            }
            is GameEvent.UpdateIsCardClicked -> {
                val isCPU = state.value.isCPU
                val isGameFinished = state.value.isGameFinished

                if (!isCPU && !isGameFinished) {
                    _state.update {
                        it.copy(
                            isCardClicked = it.isCardClicked.toMutableList().apply {
                                this[event.rowIndex] = this[event.rowIndex].toMutableList().apply {
                                    this[event.cardIndex] = event.newValue
                                }
                            },
                        )
                    }
                }
            }
            is GameEvent.UpdateCard -> {
                val isCPU = state.value.isCPU
                val isGameFinished = state.value.isGameFinished

                if (!isCPU && !isGameFinished) {
                    _state.update {
                        it.copy(
                            card = it.card.toMutableList().apply {
                                this[event.rowIndex] = this[event.rowIndex].toMutableList().apply {
                                    this[event.cardIndex] = event.newValue
                                }
                            },
                        )
                    }
                }
            }
            is GameEvent.IsOnlinePlayerTurn -> {
                _state.update { it.copy(isOnlinePlayerTurn = event.value) }
            }
            is GameEvent.UpdatePlayer1Wins -> {
                _state.update { it.copy(player1Wins = event.value) }
            }
            is GameEvent.UpdatePlayer2Wins -> {
                _state.update { it.copy(player2Wins = event.value) }
            }
            is GameEvent.UpdatePlayer1NameOnCounter -> {
                _state.update { it.copy(player1NameOnCounter = event.name) }
            }
            is GameEvent.UpdatePlayer2NameOnCounter -> {
                _state.update { it.copy(player2NameOnCounter = event.name) }
            }
            is GameEvent.UpdatePlayer1Name -> {
                _state.update { it.copy(player1Name = event.name) }
            }
            is GameEvent.UpdatePlayer2Name -> {
                _state.update { it.copy(player2Name = event.name) }
            }
            is GameEvent.UpdateCurrentPlayerName -> {
                _state.update { it.copy(currentPlayerName = event.name) }
            }
            is GameEvent.IsTimerOn -> {
                _state.update { it.copy(isTimerOn = event.value) }
            }
            GameEvent.ExecUIClickSound -> {
                settingsVM.execUIClickSound()
            }
        }
    }

    fun execCardGridSound() {
        cardGridSound(
            context,
            state.value,
            settingsVM.state.value,
            player,
        )
    }

    fun execGotOldSound() {
        gotOldSound(
            context,
            state.value,
            settingsVM.state.value,
            player,
            this::onEvent,
        )
    }

    fun execWinnerSound() {
        winnerSound(
            context,
            state.value,
            settingsVM.state.value,
            player,
            this::onEvent,
        )
    }
}
