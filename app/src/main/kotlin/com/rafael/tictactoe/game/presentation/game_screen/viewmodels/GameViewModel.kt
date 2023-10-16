package com.rafael.tictactoe.game.presentation.game_screen.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.game.domain.audio.CardGridSound
import com.rafael.tictactoe.game.domain.audio.GotOldSound
import com.rafael.tictactoe.game.domain.audio.WinnerSound
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class GameViewModel @Inject constructor(
    private val player: ExoPlayer,
    private val settingsVM: SettingsViewModel
): ViewModel() {

	private var _currentPlayerName = MutableStateFlow("")
	val currentPlayerName: StateFlow<String> = _currentPlayerName

	private var _player1Name = MutableStateFlow("")
	val player1Name: StateFlow<String> = _player1Name
	private var _player2Name = MutableStateFlow("")
	val player2Name: StateFlow<String> = _player2Name

	private var _player1Wins = MutableStateFlow(0)
	val player1Wins: StateFlow<Int> = _player1Wins
	private var _player2Wins = MutableStateFlow(0)
	val player2Wins: StateFlow<Int> = _player2Wins

	private var _card = MutableStateFlow(List(3){List<Boolean>(3){false}})
	val card: StateFlow<List<List<Boolean>>> = _card
	private var _isCardClicked = MutableStateFlow(List(3){List<Boolean>(3){false}})
	val isCardClicked: StateFlow<List<List<Boolean>>> = _isCardClicked
    
    private var _isTimerOn = MutableStateFlow(true)
	val isTimerOn: StateFlow<Boolean> = _isTimerOn
    
	private var _play = MutableStateFlow(false)
	val play: StateFlow<Boolean> = _play
	private var _isXImage = MutableStateFlow(true)
	val isXImage: StateFlow<Boolean> = _isXImage
	private var _is1Win = MutableStateFlow(false)
	val is1Win: StateFlow<Boolean> = _is1Win
	private var _isXFor1 = MutableStateFlow(true)
	val isXFor1: StateFlow<Boolean> = _isXFor1
	private var _isPlayer1 = MutableStateFlow(true)
	val isPlayer1: StateFlow<Boolean> = _isPlayer1
	private var _localPlayer1 = MutableStateFlow(true)
	val localPlayer1: StateFlow<Boolean> = _localPlayer1

	private var _isScoreUp = MutableStateFlow(false)
	val isScoreUp: StateFlow<Boolean> = _isScoreUp
	private var _isExpandButtonShown = MutableStateFlow(false)
	val isExpandButtonShown: StateFlow<Boolean> = _isExpandButtonShown
	private var _showRestartButton = MutableStateFlow(false)
	val showRestartButton: StateFlow<Boolean> = _showRestartButton
	private var _isWinSoundPlaying = MutableStateFlow(false)
	val isWinSoundPlaying: StateFlow<Boolean> = _isWinSoundPlaying
	private var _isGotOldSoundPlaying = MutableStateFlow(false)
	val isGotOldSoundPlaying: StateFlow<Boolean> = _isGotOldSoundPlaying

	private var _winner = MutableStateFlow(false)
	val winner: StateFlow<Boolean> = _winner
	private var _gotOld = MutableStateFlow(false)
	val gotOld: StateFlow<Boolean> = _gotOld

	private var _isGameFinished = MutableStateFlow(false)
	val isGameFinished: StateFlow<Boolean> = _isGameFinished

	private var _showPlayer1Name = MutableStateFlow("")
	val showPlayer1Name: StateFlow<String> = _showPlayer1Name
	private var _showPlayer2Name = MutableStateFlow("")
	val showPlayer2Name: StateFlow<String> = _showPlayer2Name

	private var _isCPU = MutableStateFlow(false)
	val isCPU: StateFlow<Boolean> = _isCPU
	private var _isCPUMode = MutableStateFlow(false)
	val isCPUMode: StateFlow<Boolean> = _isCPUMode
	private var _isCPUPlayer1 = MutableStateFlow(false)
	val isCPUPlayer1: StateFlow<Boolean> = _isCPUPlayer1

	private var _showExitGameDialog = MutableStateFlow(false)
	val showExitGameDialog: StateFlow<Boolean> = _showExitGameDialog

	private var _winningPositionsIndices = MutableStateFlow<List<Int>>(emptyList())
	val winningPositionsIndices: StateFlow<List<Int>> = _winningPositionsIndices

    private var _selectedDifficultyMode = MutableStateFlow(0)
    val selectedDifficultyMode: StateFlow<Int> = _selectedDifficultyMode

    fun updateCurrentPlayerName(newValue: String) {
        _currentPlayerName.value = newValue
    }

    fun updatePlayer1Name(newValue: String) {
        _player1Name.value = newValue
    }

    fun updatePlayer2Name(newValue: String) {
        _player2Name.value = newValue
    }

    fun updatePlayer1Wins(newValue: Int) {
        _player1Wins.value = newValue
    }

    fun updatePlayer2Wins(newValue: Int) {
        _player2Wins.value = newValue
    }

    fun resetVictoryCounter() {
        _player1Wins.value = 0
        _player2Wins.value = 0     
    }

    fun updateIsCardClicked(rowIndex: Int, cardIndex: Int, newValue: Boolean) {
        if (!isCPU.value && !isGameFinished.value) {
            _isCardClicked.value = _isCardClicked.value.toMutableList().apply {
                this[rowIndex] = this[rowIndex].toMutableList().apply {
                    this[cardIndex] = newValue
                }
            }
        }
    }

    fun updateCard(rowIndex: Int, cardIndex: Int, newValue: Boolean) {
        if (!isCPU.value && !isGameFinished.value) {
            _card.value = _card.value.toMutableList().apply {
                this[rowIndex] = this[rowIndex].toMutableList().apply {
                    this[cardIndex] = newValue
                }
            }
        }
    }

    fun updateAllCards(newCardsValue: List<List<Boolean>>) {
        _card.value = newCardsValue
    }

    fun updateAllIsCardClicked(newCardClickedValue: List<List<Boolean>>) {
        _isCardClicked.value = newCardClickedValue
    }

    fun updatePlay(newValue: Boolean) {
        _play.value = newValue
    }

    fun updateIsXImage(newValue: Boolean) {
        _isXImage.value = newValue
    }

    fun updateIs1Win(newValue: Boolean) {
        _is1Win.value = newValue
    }

    fun updateIsXFor1(newValue: Boolean) {
        _isXFor1.value = newValue
    }

    fun updateIsPlayer1(newValue: Boolean) {
        _isPlayer1.value = newValue
    }

    fun updateLocalPlayer1(newValue: Boolean) {
        _localPlayer1.value = newValue
    }

    fun updateIsScoreUp(newValue: Boolean) {
        _isScoreUp.value = newValue
    }

    fun showExpandButton(newValue: Boolean) {
        _isExpandButtonShown.value = newValue
    }

    fun showRestartButton(newValue: Boolean) {
        _showRestartButton.value = newValue
    }

    fun updateIsWinSoundPlaying(newValue: Boolean) {
        _isWinSoundPlaying.value = newValue
    }

    fun updateIsGotOldSoundPlaying(newValue: Boolean) {
        _isGotOldSoundPlaying.value = newValue
    }

    fun updateWinner(newValue: Boolean) {
        _winner.value = newValue
    }

    fun updateGotOld(newValue: Boolean) {
        _gotOld.value = newValue
    }
	
    fun showPlayer1Name(newValue: String) {
        _showPlayer1Name.value = newValue
    }

    fun showPlayer2Name(newValue: String) {
        _showPlayer2Name.value = newValue
    }

    fun updateIsGameFinished(newValue: Boolean) {
        _isGameFinished.value = newValue
    }
    
    fun updateIsTimerOn(newValue: Boolean) {
        _isTimerOn.value = newValue
    }

    fun updateIsCPU(newValue: Boolean) {
        _isCPU.value = newValue
    }

    fun updateIsCPUMode(newValue: Boolean) {
        _isCPUMode.value = newValue
    }

    fun updateIsCPUPlayer1(newValue: Boolean) {
        _isCPUPlayer1.value = newValue
    }

    fun updateWinningPositionsIndices(index: Int) {
        val currentIndices = _winningPositionsIndices.value.toMutableList()
        currentIndices.add(index)
        _winningPositionsIndices.value = currentIndices
    }

    fun resetWinningPositionsIndices(emptyList: List<Int>) {
        _winningPositionsIndices.value = emptyList
    }

    fun execCardGridSound(context: Context) {
        CardGridSound(context, this, settingsVM, player)
    }

    fun execGotOldSound(context: Context) {
        GotOldSound(context, this, settingsVM, player)
    }

    fun execWinnerSound(context: Context) {
        WinnerSound(context, this, settingsVM, player)
    }

    fun showExitGameDialog(show: Boolean) {
        _showExitGameDialog.value = show
    }

    fun updateDifficultyMode(newMode: Int) {
       _selectedDifficultyMode.value = newMode
    }

    fun play() {
        updatePlay(true)
        updateIsXImage(isXFor1.value)
        updateIsTimerOn(true)
        updateIsXImage(isXFor1.value)
    }

    fun reset() {
        val allFalse = List(3){List<Boolean>(3) { false }}
        resetVictoryCounter()
        showExpandButton(false)
        showRestartButton(false)  
        showExitGameDialog(false)
        updateAllIsCardClicked(allFalse)
        updateAllCards(allFalse)
        updateLocalPlayer1(true)
        updateIsPlayer1(true)
        updateIsCPUMode(false)
        updateIsCPUPlayer1(false)
        updateIsCPU(false)
        updateIsXImage(true)
        updateIsXFor1(true)
        showPlayer1Name("")
        showPlayer2Name("")
        updatePlayer1Name("")
        updatePlayer2Name("")
        updateWinner(false)
        updateGotOld(false)
        updateIsGameFinished(false)
        updateIsScoreUp(false)
        updateIsWinSoundPlaying(false)
        updateIsGotOldSoundPlaying(false)
        resetWinningPositionsIndices(emptyList())
        updatePlay(false)
        updateIsTimerOn(true)
        updateDifficultyMode(0)
    }
}