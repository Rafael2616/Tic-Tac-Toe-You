package com.rafael.tictactoe.feature.game.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.core.domain.util.toColor
import com.rafael.tictactoe.feature.game.domain.audio.cardGridSound
import com.rafael.tictactoe.feature.game.domain.audio.gotOldSound
import com.rafael.tictactoe.feature.game.domain.audio.winnerSound
import com.rafael.tictactoe.feature.game.domain.board.boardMovesHandler
import com.rafael.tictactoe.feature.game.domain.board.cpuMovesHandler
import com.rafael.tictactoe.feature.game.domain.checkers.Checkers
import com.rafael.tictactoe.feature.game.domain.counter.VictoryCounter
import com.rafael.tictactoe.feature.game.domain.model.CurrentPlayerName
import com.rafael.tictactoe.feature.game.domain.model.GameEvent
import com.rafael.tictactoe.feature.game.domain.model.GameMode
import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.game.domain.model.OnlineGameState
import com.rafael.tictactoe.feature.game.domain.model.Player
import com.rafael.tictactoe.feature.game.domain.model.RequestState
import com.rafael.tictactoe.feature.game.domain.names.PlayerNamesController
import com.rafael.tictactoe.feature.game.domain.names.ToastNameController
import com.rafael.tictactoe.feature.game.domain.repository.GameRepository
import com.rafael.tictactoe.feature.game.domain.restart.RestartGame
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.presentation.viewmodel.SettingsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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
    val state = _state
        .asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            GameState(),
        )
    private var onlineGameState: Flow<OnlineGameState>? = null
    private var searchPlayersJob: Job? = null

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.SetGameMode -> {
                setupGameMode(event.mode.toGameMode())
            }
            is GameEvent.GetFirstPlayerName -> {
                getFirstPlayerName(event.name)
            }
            is GameEvent.GetSecondPlayerName -> {
                getSecondPlayerName(event.name)
            }
            GameEvent.SwitchPlayerPositions -> {
                switchPlayerPositions()
            }
            GameEvent.StartGame -> {
                startGame()
            }
            is GameEvent.OnCardClick -> {
                performBoardMove(event.rowIndex, event.cardIndex)
            }
            GameEvent.ResetVictoryCounter -> {
                resetVictoryCounter()
            }
            GameEvent.PlayAgain -> {
                playAgain()
            }
            GameEvent.ExitGame -> {
                exitGame()
            }
            GameEvent.ToggleBgMusicButton -> {
                settingsVM.onEvent(SettingsEvent.ToggleIsBackgroundMusicOn)
            }
            GameEvent.ToggleSoundEffectsButton -> {
                settingsVM.onEvent(SettingsEvent.ToggleIsSoundEffectsOn)
            }
            GameEvent.ToggleExpandButton -> {
                _state.update { it.copy(isExpandButtonShown = !it.isExpandButtonShown) }
            }
            GameEvent.DismissExitDialog -> {
                _state.update { it.copy(showExitGameDialog = false) }
            }
            GameEvent.ToggleExitDialog -> {
                _state.update { it.copy(showExitGameDialog = !it.showExitGameDialog) }
            }
            GameEvent.ToggleIsXForPlayer1 -> {
                _state.update { it.copy(isXFor1 = !it.isXFor1) }
            }
            is GameEvent.SelectCPUDifficultyMode -> {
                _state.update {
                    it.copy(
                        selectedDifficultyLevel = event.level,
                        cpuDifficultyLevel = event.level.level,
                    )
                }
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
                        val randomPlayer = possiblePlayers.indices.random()
                        val onlinePlayerName = state.value.secondPlayer!!.name

                        _state.update {
                            it.copy(
                                 secondPlayer = possiblePlayers[randomPlayer],
                                 currentPlayerName = onlinePlayerName,
                                 isXFor1 = !state.value.secondPlayer!!.isXFor1,
                            )
                        }
                        _state.update {
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
                            settingsVM.onEvent(SettingsEvent.SetXColor(state.value.secondPlayer!!.color.toColor()))
                        } else {
                            settingsVM.onEvent(SettingsEvent.SetCircleColor(state.value.secondPlayer!!.color.toColor()))
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
                val value = isYouPlayer1 == 1

                _state.update {
                    it.copy(
                        isYouPlayer1 = value,
                        localPlayer1 = value,
                    )
                }
                onEvent(GameEvent.GetRemotePlayers)
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

    private fun setupGameMode(mode: GameMode) {
        _state.update { it.copy(gameMode = mode) }
        setPlayerNamesOnCounter()
        switchPlayerNamesOrder()
        updateCurrentToastName()
    }

    private fun getFirstPlayerName(name: String) {
        _state.update {
            if (state.value.localPlayer1) {
                it.copy(player1Name = name)
            } else {
                it.copy(player2Name = name)
            }
        }
        setPlayerNamesOnCounter()
        updateCurrentToastName()
    }

    private fun getSecondPlayerName(name: String) {
        _state.update {
            if (state.value.localPlayer1) {
                it.copy(player2Name = name)
            } else {
                it.copy(player1Name = name)
            }
        }
        setPlayerNamesOnCounter()
        updateCurrentToastName()
    }

    private fun switchPlayerPositions() {
        val isCPUMode = state.value.gameMode == GameMode.VsCPU
        _state.update {
            if (isCPUMode) {
                it.copy(isCPUPlayer1 = !it.isCPUPlayer1)
            } else {
                it.copy(localPlayer1 = !it.localPlayer1)
            }
        }
        switchPlayerNamesOrder()
        updateCurrentToastName()
        setPlayerNamesOnCounter()
    }

    private fun startGame() {
        val isCPUMode = state.value.gameMode == GameMode.VsCPU
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
            val randomID = (1 until 999999).random()
            _state.update {
                it.copy(
                    showSearchForPlayersSurface = true,
                    matchID = randomID,
                )
            }
            onEvent(GameEvent.UpdateIsYouPlayer1)
        }
        if (isCPUMode && state.value.isCPUPlayer1) {
            _state.update { it.copy(isCPU = true) }
            execCPUMove()
        }
        switchPlayerNamesOrder()
        setPlayerNamesOnCounter()
        updateCurrentToastName()
    }

    private fun resetVictoryCounter() {
        _state.update {
            it.copy(
                showVictoryCounterReset = false,
                player1Wins = 0,
                player2Wins = 0,
            )
        }
    }

    private fun playAgain() {
        val isCPUMode = state.value.gameMode == GameMode.VsCPU
        val allFalse = List(3) { List(3) { false } }
        val restartGame = RestartGame()
        val checkers = Checkers()
        _state.update {
            it.copy(
                isExpandButtonShown = false,
                isWinSoundPlaying = false,
                isGotOldSoundPlaying = false,
                hasWinner = false,
                gotOld = false,
                isGameFinished = false,
                isCardClicked = allFalse,
                winningPositionsIndices = emptyList(),
                showRestartButton = false,
                hasLastWinningPlayer = checkers.hasLastWinner(state.value),
                isXImage = restartGame.resetXImage(state.value),
                isPlayer1 = restartGame.resetPlayer(state.value),
            )
        }
        if (isCPUMode) {
            _state.update { it.copy(isCPU = restartGame.resetIsPlayerCPU(state.value)) }
            if (state.value.isCPU) {
                execCPUMove()
            }
        }
        updateCurrentToastName()
    }

    private fun updateCard(rowIndex: Int, cardIndex: Int, newValue: Boolean) {
        val isGameFinished = state.value.isGameFinished

        if (!isGameFinished) {
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

    private fun updateIsCardClicked(rowIndex: Int, cardIndex: Int, newValue: Boolean) {
        val isGameFinished = state.value.isGameFinished

        if (!isGameFinished) {
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

    private fun updateGameState() {
        val checkers = Checkers()
        val counter = VictoryCounter()
        val restart = RestartGame()
        _state.update {
            it.copy(
                isExpandButtonShown = false,
                isGameFinished = checkers.checkGameFinished(state.value),
                gotOld = checkers.checkIfGotOld(state.value),
                showVictoryCounterReset = counter.shouldShowVictoryCounterReset(state.value),
                hasWinner = checkers.checkWinner(state.value).first,
                showRestartButton = restart.shouldShowRestartButton(state.value),
            )
        }

        if (state.value.hasWinner) {
            _state.update {
                it.copy(
                    is1Win = checkers.whoPlayerWin(state.value),
                    winningPositionsIndices = checkers.checkWinner(state.value).third,
                )
            }
        }
        onGameFinish(checkers.checkGameFinished(state.value))
    }

    private fun performBoardMove(rowIndex: Int, cardIndex: Int) {
        val moveParams = boardMovesHandler(
            state.value,
            rowIndex,
            cardIndex,
        )
        moveParams?.let { param ->
            execCardGridSound()

            @Suppress("UNCHECKED_CAST")
            val card = param["card"] as List<Any>

            @Suppress("UNCHECKED_CAST")
            val cardClicked = param["isCardClicked"] as List<Any>

            _state.update {
                it.copy(
                    isOnlinePlayerTurn = param["isOnlinePlayerTurn"] as Boolean? ?: state.value.isOnlinePlayerTurn,
                    isXImage = param["isXImage"] as Boolean,
                    isCPU = param["isCPU"] as Boolean? ?: state.value.isCPU,
                    isPlayer1 = param["isPlayer1"] as Boolean,
                )
            }
            updateCard(card[0] as Int, card[1] as Int, card[2] as Boolean)
            updateIsCardClicked(cardClicked[0] as Int, cardClicked[1] as Int, cardClicked[2] as Boolean)
            if (state.value.gameMode == GameMode.VsCPU && param["isCPU"] as Boolean) {
                execCPUMove()
            }
            updateGameState()
            updateCurrentToastName()
            switchPlayerNamesOrder()
        }
        _state.update { it.copy(isExpandButtonShown = false) }
    }

    private fun execCPUMove() {
        val delayTime = if (state.value.isTimerOn) 2300L else 570L
        viewModelScope.launch {
            delay(delayTime)
            val moveParams = cpuMovesHandler(state.value)

            moveParams?.let { param ->
                execCardGridSound()

                @Suppress("UNCHECKED_CAST")
                val card = param["card"] as List<Any>

                @Suppress("UNCHECKED_CAST")
                val cardClicked = param["isCardClicked"] as List<Any>

                _state.update {
                    it.copy(
                        isUsingEasyLevel = param["isUsingEasyLevel"] as Boolean? ?: state.value.isUsingEasyLevel,
                        isXImage = param["isXImage"] as Boolean,
                        isCPU = param["isCPU"] as Boolean,
                        isPlayer1 = param["isPlayer1"] as Boolean,
                    )
                }
                updateCard(card[0] as Int, card[1] as Int, card[2] as Boolean)
                updateIsCardClicked(cardClicked[0] as Int, cardClicked[1] as Int, cardClicked[2] as Boolean)
                updateGameState()
                updateCurrentToastName()
                switchPlayerNamesOrder()
            }
        }
    }

    private fun setPlayerNamesOnCounter() {
        val counter = VictoryCounter()
        _state.update {
            it.copy(
                player1NameOnCounter = counter.setFirstName(state.value, context),
                player2NameOnCounter = counter.setSecondName(state.value, context),
            )
        }
    }

    private fun switchPlayerNamesOrder() {
        PlayerNamesController().currentPlayerNames(state.value, context).let { (player1Name, player2Name) ->
            _state.update {
                it.copy(
                    player1Name = player1Name,
                    player2Name = player2Name,
                )
            }
        }
    }

    private fun updateCurrentToastName() {
        val toastController = ToastNameController()
        viewModelScope.launch {
            toastController.currentToastName(state.value, context)?.let { name ->
                _state.update { it.copy(currentPlayerName = name) }
            }
            if (toastController.getCurrentGamePhase(state.value) == CurrentPlayerName.CounterAfterGameStarts) {
                toastController.counterAfterGameStarts(state.value).collect { (name, isTimerOn) ->
                    _state.update {
                        it.copy(
                            currentPlayerName = name,
                            isTimerOn = isTimerOn,
                        )
                    }
                    if (!isTimerOn) {
                        updateCurrentToastName()
                    }
                }
            }
        }
    }

    private fun onGameFinish(isGameFinished: Boolean) {
        _state.update {
            it.copy(
                isGameFinished = isGameFinished,
                showRestartButton = RestartGame().shouldShowRestartButton(state.value),
            )
        }
        if (state.value.hasWinner) {
            val (player1Wins, player2Wins) = VictoryCounter().updateVictoryCounter(state.value)
            _state.update {
                it.copy(
                    player1Wins = player1Wins,
                    player2Wins = player2Wins,
                )
            }
            execWinnerSound()
        }
        if (state.value.gotOld) {
            execGotOldSound()
        }
    }

    private fun exitGame() {
        settingsVM.onEvent(SettingsEvent.ShowColorSelectionDialog(false))
        val cardState = state.value.card
        _state.update { GameState() }
        searchPlayersJob?.cancel()
        searchPlayersJob = null

        // Keep card variable state
        _state.update { it.copy(card = cardState) }
    }

    private fun execCardGridSound() {
        cardGridSound(
            context,
            state.value,
            settingsVM.state.value,
            player,
        )
    }

    private fun execGotOldSound() {
        val isPlaying = gotOldSound(
            context,
            state.value,
            settingsVM.state.value,
            player,
        )
        _state.update { it.copy(isGotOldSoundPlaying = isPlaying) }
    }

    private fun execWinnerSound() {
        val isPlaying = winnerSound(
            context,
            state.value,
            settingsVM.state.value,
            player,
        )
        _state.update { it.copy(isWinSoundPlaying = isPlaying) }
    }

    private fun String.toGameMode(): GameMode = when (this) {
        "PvP" -> GameMode.PvP
        "Online" -> GameMode.Online
        "VsCPU" -> GameMode.VsCPU
        else -> GameMode.PvP
    }
}
