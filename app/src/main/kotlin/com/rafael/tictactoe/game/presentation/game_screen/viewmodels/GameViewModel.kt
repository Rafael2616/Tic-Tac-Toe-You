package com.rafael.tictactoe.game.presentation.game_screen.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.game.domain.audio.CardGridSound
import com.rafael.tictactoe.game.domain.audio.GotOldSound
import com.rafael.tictactoe.game.domain.audio.WinnerSound
import com.rafael.tictactoe.game.domain.card_grid.CardGridClickHandler
import com.rafael.tictactoe.game.domain.card_grid.EasyModeCPUCardClick
import com.rafael.tictactoe.game.domain.model.GameEvent
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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class GameViewModel @Inject constructor(
    private val player: ExoPlayer,
    private val settingsVM: SettingsViewModel,
    private val context: Application
): ViewModel() {

    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.getFirstPlayerName -> {
                val localPlayer1  = state.value.localPlayer1

                _state.update { 
                    if (localPlayer1) {
                        it.copy(player1Name = event.name)
                    } else {
                        it.copy(player2Name = event.name)
                    }
                }
            }
            is GameEvent.getSecondPlayerName -> {
                val localPlayer1 = state.value.localPlayer1

                _state.update {
                    if (localPlayer1) {
                        it.copy(player2Name = event.name)
                    } else {
                        it.copy(player1Name = event.name)
                    }
                }
            }
            GameEvent.switchPlayerPositions -> {
                val isCPUMode = state.value.isCPUMode
                val isCPUPlayer1 = state.value.isCPUPlayer1
                val localPlayer1 = state.value.localPlayer1

                _state.update {
                    if (!isCPUMode) {
                        it.copy(localPlayer1 = !localPlayer1)
                    } else {
                        it.copy(isCPUPlayer1 = !isCPUPlayer1)
                    }
                }
                settingsVM.execUIClickSound()
            }
            GameEvent.startGame -> {
                val isXFor1 = state.value.isXFor1
                val isCPUMode = state.value.isCPUMode
                val isCPUPlayer1 = state.value.isCPUPlayer1

                _state.update {
                    it.copy(
                        play = true,
                        isXImage = isXFor1,
                        isTimerOn = true
                    )
                }
                if (isCPUMode && isCPUPlayer1) {
                   _state.update { it.copy(isCPU = true) }
                   EasyModeCPUCardClick(this, state.value)
                }
                settingsVM.execUIClickSound()
            }
            is GameEvent.clickOnCardGrid -> {
                _state.update { it.copy(
                	isExpandButtonShown = false
                )}
                viewModelScope.launch(Dispatchers.IO) {
                    CardGridClickHandler(
                        state.value,
                        this@GameViewModel,
                        event.rowIndex,
                        event.cardIndex
                    )
                }
                execCardGridSound()
            }
            GameEvent.resetVictoryCounter -> {
                _state.update { it.copy(
                    showVictoryCounterReseter = false,
                    player1Wins = 0,
                    player2Wins = 0
                )}
                settingsVM.execUIClickSound()
            }
            GameEvent.playAgain -> {
                val isCPUMode = state.value.isCPUMode

                _state.update { it.copy(isExpandButtonShown = false)}
                settingsVM.execUIClickSound()
                RestartGame(this, state.value)

                if (isCPUMode) {
                    EasyModeCPUCardClick(this, state.value)
                }
            }
            GameEvent.toggleBgMusicButton -> {
                settingsVM.onEvent(SettingsEvent.toggleIsBackgroundMusicOn)
            }
            GameEvent.toggleSoundEffectsButton -> {
                settingsVM.onEvent(SettingsEvent.toggleIsSoundEffectsOn)
            }
            GameEvent.toggleExpandButton -> {
                val isExpandButtonShown = state.value.isExpandButtonShown
                _state.update { it.copy(isExpandButtonShown = !isExpandButtonShown)}
            }
            GameEvent.exitGame -> {
                settingsVM.execUIClickSound()
                settingsVM.onEvent(SettingsEvent.showColorSelectionDialog(false))
                _state.update { GameState() }
            }
            GameEvent.dimissExitDialog -> {
                settingsVM.execUIClickSound()
                _state.update { it.copy(showExitGameDialog = false )}
            }
            GameEvent.toggleExitDialog -> {
                val showExitGameDialog = state.value.showExitGameDialog
                _state.update { it.copy(showExitGameDialog = !showExitGameDialog )}
            }
            is GameEvent.updateIsGotOldSoundPlaying -> {
                _state.update { it.copy(isGotOldSoundPlaying = event.value )}
            }
            is GameEvent.updateIsWinSoundPlaying -> {
                _state.update { it.copy(isWinSoundPlaying = event.value )}
            }
        }
    }

    fun updateIsCardClicked(rowIndex: Int, cardIndex: Int, newValue: Boolean) {
        val isCPU = state.value.isCPU
        val isGameFinished = state.value.isGameFinished
        var isCardClicked = state.value.isCardClicked

        if (!isCPU && !isGameFinished) {
            _state.update { it.copy(
                isCardClicked = isCardClicked.toMutableList().apply {
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
        var card = state.value.card

        if (!isCPU && !isGameFinished) {
            _state.update { it.copy(
                card = card.toMutableList().apply {
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

    fun updateWinner(newValue: Boolean) {
        _state.update { it.copy(winner = newValue)}
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

    fun updateDifficultyMode(newMode: Int) {
        _state.update { it.copy(selectedDifficultyMode = newMode)}
    }

    fun showVictoryCounterReseter(newValue:  Boolean) {
        _state.update { it.copy(showVictoryCounterReseter = newValue)}
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