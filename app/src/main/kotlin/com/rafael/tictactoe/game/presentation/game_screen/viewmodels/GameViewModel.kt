package com.rafael.tictactoe.game.presentation.game_screen.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.rafael.tictactoe.game.domain.util.GameUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameUtil : GameUtils
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
	private var _isXImage = MutableStateFlow(false)
	val isXImage: StateFlow<Boolean> = _isXImage
	private var _is1Win = MutableStateFlow(false)
	val is1Win: StateFlow<Boolean> = _is1Win
	private var _isXFor1 = MutableStateFlow(false)
	val isXFor1: StateFlow<Boolean> = _isXFor1
	private var _isPlayer1 = MutableStateFlow(true)
	val isPlayer1: StateFlow<Boolean> = _isPlayer1

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

	private var _startX = MutableStateFlow(0f)
	val startX: StateFlow<Float> = _startX
	private var _startY = MutableStateFlow(0f)
	val startY: StateFlow<Float> = _startY
	private var _endX = MutableStateFlow(0f)
	val endX: StateFlow<Float> = _endX
	private var _endY = MutableStateFlow(0f)
	val endY: StateFlow<Float> = _endY

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

	private var _playerName = MutableStateFlow("")
	val playerName: StateFlow<String> = _playerName

	private var _isCPU = MutableStateFlow(false)
	val isCPU: StateFlow<Boolean> = _isCPU

	private var _isCPUMode = MutableStateFlow(false)
	val isCPUMode: StateFlow<Boolean> = _isCPUMode

    fun updatePlayerName(newValue: String) {
        _playerName.value = newValue
    }

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

    fun updateIsCardClicked(rowIndex: Int, cardIndex: Int, newValue: Boolean) {
        if (!_isGameFinished.value) {
        _isCardClicked.value = _isCardClicked.value.toMutableList().apply {
            this[rowIndex] = this[rowIndex].toMutableList().apply {
                this[cardIndex] = newValue
            }
        }
    }

    fun updateCard(rowIndex: Int, cardIndex: Int, newValue: Boolean) {
        if (!_isGameFinished.value) {
        _card.value = _card.value.toMutableList().apply {
            this[rowIndex] = this[rowIndex].toMutableList().apply {
                this[cardIndex] = newValue
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

    fun updateIsScoreUp(newValue: Boolean) {
        _isScoreUp.value = newValue
    }

    fun updateIsExpandButtonShown(newValue: Boolean) {
        _isExpandButtonShown.value = newValue
    }

    fun updateShowRestartButton(newValue: Boolean) {
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

    fun setCoordinates(sX: Float, sY: Float, eX: Float, eY: Float) {     
        _startX.value = sX
        _startY.value = sY
        _endX.value = eX
        _endY.value = eY
    }
}