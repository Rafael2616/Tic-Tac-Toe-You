package com.rafael.tictactoe.game.presentation.gameScreen.viewmodels

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
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
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
    var availablePlayers: Flow<List<Player>> = flow { emit(emptyList()) }
    var onlineGameState: Flow<OnlineGameState>? = null

    fun onEvent(event: GameEvent) {
        val localPlayer1 = state.value.localPlayer1
        val isCPUMode = state.value.isCPUMode
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
            is GameEvent.StartGame -> {
                _state.update {
                    it.copy(
                        isXImage = it.isXFor1,
                        player1Name = event.player1Value,
                        player2Name = event.player2Value,
                        showGetPrefsDialog = false,
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
                    val randomicID = (1 until 99999).random()
                    _state.update {
                        it.copy(
                            showSearchForPlayersSurface = true,
                            matchID = randomicID,
                        )
                    }
                    this@GameViewModel.onEvent(GameEvent.UpdateIsYouPlayer1)
                    this@GameViewModel.onEvent(GameEvent.GetRemotePlayers)
                }
                if (isCPUMode && isCPUPlayer1) {
                    _state.update { it.copy(isCPU = true) }
                    cpuMoveLevelsHandler(
                        this,
                        state.value,
                        this::onEvent,
                    )
                }
                settingsVM.execUIClickSound()
            }
            is GameEvent.ClickOnGameBoard -> {
                _state.update { it.copy(isExpandButtonShown = false) }
                boardMovesHandler(
                    state.value,
                    this::onEvent,
                    this@GameViewModel,
                    event.rowIndex,
                    event.cardIndex,
                )
                execCardGridSound()
            }
            GameEvent.ExecCPUMove -> {
                cpuMoveLevelsHandler(
                    this,
                    state.value,
                    this::onEvent,
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
                    this,
                    state.value,
                )
                if (isCPUMode) {
                    cpuMoveLevelsHandler(
                        this,
                        state.value,
                        this::onEvent,
                    )
                }
                settingsVM.execUIClickSound()
            }
            GameEvent.ToggleBgMusicButton -> {
                settingsVM.onEvent(SettingsEvent.ToggleIsBackgroundMusicOn)
            }
            GameEvent.ToggleSoundEffectsButton -> {
                settingsVM.onEvent(SettingsEvent.ToggleIsSoundEffectsOn)
            }
            GameEvent.ToggleExpandButton -> {
                settingsVM.execUIClickSound()
                _state.update { it.copy(isExpandButtonShown = !it.isExpandButtonShown) }
            }
            GameEvent.ExitGame -> {
                settingsVM.onEvent(SettingsEvent.ShowColorSelectionDialog(false))
                val cardState = state.value.card
                _state.update { GameState() }

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
            is GameEvent.UpdateIsGotOldSoundPlaying -> {
                _state.update { it.copy(isGotOldSoundPlaying = event.value) }
            }
            is GameEvent.UpdateIsWinSoundPlaying -> {
                _state.update { it.copy(isWinSoundPlaying = event.value) }
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
                viewModelScope.launch(Dispatchers.IO) {
                    val response = repository.getRemotePlayers()
                    if (response.first != null) {
                        availablePlayers = response.first!!
                        val possiblePlayers: List<Player> = availablePlayers.map {
                            it.filter { players ->
                                players.isPlayer1 == !state.value.isYouPlayer1
                            }
                        }.first()
                        val randomPlayer = (0..possiblePlayers.size - 1).random()
                        _state.update { it.copy(secondPlayer = possiblePlayers[randomPlayer]) }
                    }
                    _state.update { it.copy(requestState = response.second) }
                }
            }
            GameEvent.PostRemotePlayer -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val response = repository.postPlayer(state.value, settingsVM.state.value)
                    _state.update { it.copy(requestState = response.second) }
                }
            }
            GameEvent.RemoveRemotePlayer -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val response = repository.removePlayer(state.value.matchID)
                    _state.update { it.copy(requestState = response) }
                }
            }
            GameEvent.SendInitialOnlineGameState -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val response = repository.postInitialGameState(state.value.matchID)
                    _state.update { it.copy(requestState = response) }
                }
            }
            GameEvent.GetOnlineGameState -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val response = repository.getGameState()
                    if (response.first != null) {
                        onlineGameState = response.first
                        _state.update {
                            it.copy(
                                requestState = RequestState.Success,
                            /*player1Wins = onlineGameState.player1Wins.value,
                            player2Wins = onlineGameState.player2Wins.value,
                            card = onlineGameState.card.value,
                            isCardClicked = onlineGameState.isCardClicked.value,
                            play = onlineGameState.play.value,
                            isPlayer1 = onlineGameState.isPlayer1.value,
                            hasWinner = onlineGameState.hasWinner.value,
                            gotOld = onlineGameState.gotOld.value,
                            isGameFinished = onlineGameState.isGameFinished.value*/
                            )
                        }
                    }
                    _state.update { it.copy(requestState = response.second) }
                }
            }
            GameEvent.UpdateOnlineGameState -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val response = repository.updateGameState(state.value)
                    _state.update { it.copy(requestState = response.second) }
                    onlineGameState = response.first!!
                }
            }
            GameEvent.EndOnlineMatch -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val response = repository.endMatch(state.value.matchID)
                    _state.update { it.copy(requestState = response) }
                }
            }
            is GameEvent.UpdateRequestState -> {
                _state.update { it.copy(requestState = event.state) }
            }
            GameEvent.UpdateIsYouPlayer1 -> {
                val isYouPlayer1 = (1..3).random()
                val value = if (isYouPlayer1 == 1) true else false

                _state.update {
                    it.copy(
                        isYouPlayer1 = value,
                        localPlayer1 = value,
                    )
                }
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
        }
    }

    fun updateIsCardClicked(rowIndex: Int, cardIndex: Int, newValue: Boolean) {
        val isCPU = state.value.isCPU
        val isGameFinished = state.value.isGameFinished

        if (!isCPU && !isGameFinished) {
            _state.update {
                it.copy(
                    isCardClicked = it.isCardClicked.toMutableList().apply {
                        this[rowIndex] = this[rowIndex].toMutableList().apply {
                            this[cardIndex] = newValue
                        }
                    },
                )
            }
        }
    }

    fun updateCard(rowIndex: Int, cardIndex: Int, newValue: Boolean) {
        val isCPU = state.value.isCPU
        val isGameFinished = state.value.isGameFinished

        if (!isCPU && !isGameFinished) {
            _state.update {
                it.copy(
                    card = it.card.toMutableList().apply {
                        this[rowIndex] = this[rowIndex].toMutableList().apply {
                            this[cardIndex] = newValue
                        }
                    },
                )
            }
        }
    }

    fun updateCurrentPlayerName(newValue: String) {
        _state.update { it.copy(currentPlayerName = newValue) }
    }

    fun updatePlayer1Name(newValue: String) {
        _state.update { it.copy(player1Name = newValue) }
    }

    fun updatePlayer2Name(newValue: String) {
        _state.update { it.copy(player2Name = newValue) }
    }

    fun updatePlayer1Wins(newValue: Int) {
        _state.update { it.copy(player1Wins = newValue) }
    }

    fun updatePlayer2Wins(newValue: Int) {
        _state.update { it.copy(player2Wins = newValue) }
    }

    fun updateAllIsCardClicked(newCardClickedValue: List<List<Boolean>>) {
        _state.update { it.copy(isCardClicked = newCardClickedValue) }
    }

    fun updateIsXImage(newValue: Boolean) {
        _state.update { it.copy(isXImage = newValue) }
    }

    fun updateIs1Win(newValue: Boolean) {
        _state.update { it.copy(is1Win = newValue) }
    }

    fun updateIsXFor1(newValue: Boolean) {
        _state.update { it.copy(isXFor1 = newValue) }
    }

    fun updateIsPlayer1(newValue: Boolean) {
        _state.update { it.copy(isPlayer1 = newValue) }
    }

    fun updateIsScoreUp(newValue: Boolean) {
        _state.update { it.copy(isScoreUp = newValue) }
    }

    fun showRestartButton(newValue: Boolean) {
        _state.update { it.copy(showRestartButton = newValue) }
    }

    fun updateIsWinSoundPlaying(newValue: Boolean) {
        _state.update { it.copy(isWinSoundPlaying = newValue) }
    }

    fun updateIsGotOldSoundPlaying(newValue: Boolean) {
        _state.update { it.copy(isGotOldSoundPlaying = newValue) }
    }

    fun updateHasWinner(newValue: Boolean) {
        _state.update { it.copy(hasWinner = newValue) }
    }

    fun updateGotOld(newValue: Boolean) {
        _state.update { it.copy(gotOld = newValue) }
    }
	
    fun updatePlayer1NameOnCounter(newValue: String) {
        _state.update { it.copy(player1NameOnCounter = newValue) }
    }

    fun updatePlayer2NameOnCounter(newValue: String) {
        _state.update { it.copy(player2NameOnCounter = newValue) }
    }

    fun updateIsGameFinished(newValue: Boolean) {
        _state.update { it.copy(isGameFinished = newValue) }
    }

    fun updateIsTimerOn(newValue: Boolean) {
        _state.update { it.copy(isTimerOn = newValue) }
    }

    fun updateIsCPU(newValue: Boolean) {
        _state.update { it.copy(isCPU = newValue) }
    }

    fun updateIsCPUMode(newValue: Boolean) {
        _state.update { it.copy(isCPUMode = newValue) }
    }

    fun updateWinningPositionsIndices(index: Int) {
        val currentIndices = state.value.winningPositionsIndices.toMutableList()
        currentIndices.add(index)
        _state.update { it.copy(winningPositionsIndices = currentIndices) }
    }

    fun resetWinningPositionsIndices(emptyList: List<Int>) {
        _state.update { it.copy(winningPositionsIndices = emptyList) }
    }

    fun showVictoryCounterReseter(newValue: Boolean) {
        _state.update { it.copy(showVictoryCounterReseter = newValue) }
    }

    fun updateHasLastWinningPlayer(player: Boolean) {
        _state.update { it.copy(hasLastWinningPlayer = player) }
    }

    fun updateIsOnlinePlayerTurn(newValue: Boolean) {
        _state.update { it.copy(isOnlinePlayerTurn = newValue) }
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
