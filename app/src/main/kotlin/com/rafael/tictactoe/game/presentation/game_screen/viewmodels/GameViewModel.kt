package com.rafael.tictactoe.game.presentation.game_screen.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.game.domain.audio.CardGridSound
import com.rafael.tictactoe.game.domain.audio.GotOldSound
import com.rafael.tictactoe.game.domain.audio.WinnerSound
import com.rafael.tictactoe.game.domain.card_grid.boardMovesHandler
import com.rafael.tictactoe.game.domain.card_grid.cpuMoveLevelsHandler
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.domain.restart_game.RestartGame
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class GameViewModel @Inject constructor(
    private val player: ExoPlayer,
    private val settingsVM: SettingsViewModel,
    private val context: Application
): ViewModel() {

    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()

    fun onEvent(event: GameEvent) {
        val localPlayer1  = state.value.localPlayer1
        val isCPUMode = state.value.isCPUMode
        val isCPUPlayer1 = state.value.isCPUPlayer1

        when (event) {
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
                _state.update { it.copy(
                    play = true,
                    isXImage = it.isXFor1,
                    isTimerOn = true,
                    player1Name = event.player1Value,
                    player2Name = event.player2Value)
                }
                if (isCPUMode && isCPUPlayer1) {
                   _state.update { it.copy(isCPU = true) }
                   cpuMoveLevelsHandler(
                       this,
                       state.value,
                       this::onEvent
                   )
                }
                if (isCPUMode) {
                    _state.update { it.copy(gameMode = GameMode.VsCPU) }
                } else {
                    _state.update { it.copy(gameMode = GameMode.PvP) }
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
                    event.cardIndex
                )
                execCardGridSound()
            }
            GameEvent.ExecCPUMove -> {
                cpuMoveLevelsHandler(
                    this,
                    state.value,
                    this::onEvent
                )
            }
            GameEvent.ResetVictoryCounter -> {
                _state.update { it.copy(
                    showVictoryCounterReseter = false,
                    player1Wins = 0,
                    player2Wins = 0)
                }
                settingsVM.execUIClickSound()
            }
            GameEvent.PlayAgain -> {
                _state.update { it.copy(isExpandButtonShown = false) }     
                RestartGame(
                    this,
                    state.value
                )
                if (isCPUMode) {
                    cpuMoveLevelsHandler(
                        this,
                        state.value,
                        this::onEvent
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
                _state.update { it.copy(isExpandButtonShown = !it.isExpandButtonShown)}
            }
            GameEvent.ExitGame -> {
                settingsVM.onEvent(SettingsEvent.ShowColorSelectionDialog(false))
                val cardState = state.value.card
                _state.update { GameState() }

                //Keep card variable state
                _state.update { it.copy(card = cardState)}
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
                _state.update { it.copy(cpuDifficultyLevel = event.level) }
                _state.update { it.copy(selectedDifficultyLevel = event.selectedLevel) }
                _state.update { it.copy(difficultyLevelName = event.name) }
                _state.update { it.copy(difficultyLevelIcon = event.icon) }
                settingsVM.execUIClickSound()
            }
            is GameEvent.UseEasyLevel -> {
                _state.update { it.copy(isUsingEasyLevel = event.value) }
            }
        }
    }

    fun updateIsCardClicked(rowIndex: Int, cardIndex: Int, newValue: Boolean) {
        val isCPU = state.value.isCPU
        val isGameFinished = state.value.isGameFinished

        if (!isCPU && !isGameFinished) {
            _state.update { it.copy(
                isCardClicked = it.isCardClicked.toMutableList().apply {
                    this[rowIndex] = this[rowIndex].toMutableList().apply {
                        this[cardIndex] = newValue
                    }
                }
            )}
        }
    }

    fun updateCard(rowIndex: Int, cardIndex: Int, newValue: Boolean) {
        val isCPU = state.value.isCPU
        val isGameFinished = state.value.isGameFinished

        if (!isCPU && !isGameFinished) {
            _state.update { it.copy(
                card = it.card.toMutableList().apply {
                    this[rowIndex] = this[rowIndex].toMutableList().apply {
                        this[cardIndex] = newValue
                    }
                }
            )}
        }
    }

    fun updateCurrentPlayerName(newValue: String) {
        _state.update { it.copy(currentPlayerName = newValue)}
    }

    fun updatePlayer1Name(newValue: String) {
        _state.update { it.copy(player1Name = newValue)}
    }

    fun updatePlayer2Name(newValue: String) {
        _state.update { it.copy(player2Name = newValue)}
    }

    fun updatePlayer1Wins(newValue: Int) {
        _state.update { it.copy(player1Wins = newValue)}
    }

    fun updatePlayer2Wins(newValue: Int) {
        _state.update { it.copy(player2Wins = newValue)}
    }

    fun updateAllIsCardClicked(newCardClickedValue: List<List<Boolean>>) {
        _state.update { it.copy(isCardClicked = newCardClickedValue)}
    }

    fun updateIsXImage(newValue: Boolean) {
        _state.update { it.copy(isXImage = newValue)}
    }

    fun updateIs1Win(newValue: Boolean) {
        _state.update { it.copy(is1Win = newValue)}
    }

    fun updateIsXFor1(newValue: Boolean) {
        _state.update { it.copy(isXFor1 = newValue)}
    }

    fun updateIsPlayer1(newValue: Boolean) {
        _state.update { it.copy(isPlayer1 = newValue)}
    }

    fun updateIsScoreUp(newValue: Boolean) {
        _state.update { it.copy(isScoreUp = newValue)}
    }

    fun showRestartButton(newValue: Boolean) {
        _state.update { it.copy(showRestartButton = newValue)}
    }

    fun updateIsWinSoundPlaying(newValue: Boolean) {
        _state.update { it.copy(isWinSoundPlaying = newValue)}
    }

    fun updateIsGotOldSoundPlaying(newValue: Boolean) {
        _state.update { it.copy(isGotOldSoundPlaying = newValue)}
    }

    fun updateHasWinner(newValue: Boolean) {
        _state.update { it.copy(hasWinner = newValue)}
    }

    fun updateGotOld(newValue: Boolean) {
        _state.update { it.copy(gotOld = newValue)}
    }
	
    fun showPlayer1Name(newValue: String) {
        _state.update { it.copy(showPlayer1Name = newValue)}
    }

    fun showPlayer2Name(newValue: String) {
        _state.update { it.copy(showPlayer2Name = newValue)}
    }

    fun updateIsGameFinished(newValue: Boolean) {
        _state.update { it.copy(isGameFinished = newValue)}
    }
    
    fun updateIsTimerOn(newValue: Boolean) {
        _state.update { it.copy(isTimerOn = newValue)}
    }

    fun updateIsCPU(newValue: Boolean) {
        _state.update { it.copy(isCPU = newValue)}
    }

    fun updateIsCPUMode(newValue: Boolean) {
        _state.update { it.copy(isCPUMode = newValue)}
    }

    fun updateWinningPositionsIndices(index: Int) {
        val currentIndices = state.value.winningPositionsIndices.toMutableList()
        currentIndices.add(index)
        _state.update { it.copy(winningPositionsIndices = currentIndices)}
    }

    fun resetWinningPositionsIndices(emptyList: List<Int>) {
        _state.update { it.copy(winningPositionsIndices = emptyList)}
    }

    fun showVictoryCounterReseter(newValue: Boolean) {
        _state.update { it.copy(showVictoryCounterReseter = newValue)}
    }

    fun updateHasLastWinningPlayer(player: Boolean) {
         _state.update { it.copy(hasLastWinningPlayer = player)}
    }

    fun execCardGridSound() {
        CardGridSound(
            context,
            state.value,
            settingsVM.state.value,
            player
        )
    }

    fun execGotOldSound() {
        GotOldSound(
            context,
            state.value,
            settingsVM.state.value,
            player,
            this::onEvent
        )
    }

    fun execWinnerSound() {
        WinnerSound(
            context,
            state.value,
            settingsVM.state.value,
            player,
            this::onEvent
        )
    }
}