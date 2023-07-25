package com.rafael.tictactoe.game.presentation.game_screen.viewmodels

import android.content.Context
import androidx.lifecycle.*
import com.rafael.tictactoe.game.domain.util.GameUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

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

	private var _cards = MutableStateFlow(List<Boolean>(9){false})
	val cards: StateFlow<List<Boolean>> = _cards
	private var _isCardClicked = MutableStateFlow(List<Boolean>(9){false})
	val isCardClicked: StateFlow<List<Boolean>> = _isCardClicked

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
	private var _player1InToast = MutableStateFlow(true)
	val player1InToast: StateFlow<Boolean> = _player1InToast

	private var _isScoreUp = MutableStateFlow(false)
	val isScoreUp: StateFlow<Boolean> = _isScoreUp
	private var _expandButton = MutableStateFlow(false)
	val expandButton: StateFlow<Boolean> = _expandButton
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

	private var _showPlayer1Name = MutableStateFlow("")
	val showPlayer1Name: StateFlow<String> = _showPlayer1Name
	private var _showPlayer2Name = MutableStateFlow("")
	val showPlayer2Name: StateFlow<String> = _showPlayer2Name

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

	fun updateCard(index: Int, newValue: Boolean) {
	    _cards.value = _cards.value.toMutableList().also {
			it[index] = newValue
		}
	}

	fun updateIsCardClicked(index: Int, newValue: Boolean) {
 	   _isCardClicked.value = _isCardClicked.value.toMutableList().also {
 	  	 it[index] = newValue
	    }
	}

    fun updateAllCards(newCardsValue: List<Boolean>) {
        _cards.value = newCardsValue
    }

    fun updateAllIsCardClicked(newCardClickedValue: List<Boolean>) {
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

    fun updatePlayer1InToast(newValue: Boolean) {
        _player1InToast.value = newValue
    }

    fun updateIsScoreUp(newValue: Boolean) {
        _isScoreUp.value = newValue
    }

    fun updateExpandButton(newValue: Boolean) {
        _expandButton.value = newValue
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

    fun setCoordinates(sX: Float, sY: Float, eX: Float, eY: Float) {     
        _startX.value = sX
        _startY.value = sY
        _endX.value = eX
        _endY.value = eY
    }
}