package com.rafael.tictactoe.game.presentation.game_screen.viewmodels

import android.content.Context
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import com.rafael.tictactoe.game.domain.repository.GameRepository


@HiltViewModel
class GameViewModel @Inject constructor(
private val repository: GameRepository
) : ViewModel() {

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

private var _cards = MutableStateFlow(emptyList<Boolean>())
val cards: StateFlow<List<Boolean>> = _cards

private var _isCardClicked = MutableStateFlow(emptyList<Boolean>())
val isCardClicked: StateFlow<List<Boolean>> = _isCardClicked

private var _play = MutableStateFlow(false)
val play: StateFlow<Boolean> = _play

private var _isXImage = MutableStateFlow(false)
val isXImage: StateFlow<Boolean> = _isXImage

private var _is1Win = MutableStateFlow(false)
val is1Win: StateFlow<Boolean> = _is1Win

private var _isXFor1 = MutableStateFlow(false)
val isXFor1: StateFlow<Boolean> = _isXFor1

private var _isPlayer1 = MutableStateFlow(false)
val isPlayer1: StateFlow<Boolean> = _isPlayer1

private var _player1InToast = MutableStateFlow(false)
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


    fun updateCurrentPlayerName(newValue: String) {
        _currentPlayerName.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setPlayer1Name(newValue)
        }
    }

    fun updatePlayer1Name(newValue: String) {
        _player1Name.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setPlayer1Name(newValue)
        }
    }

    fun updatePlayer2Name(newValue: String) {
        _player2Name.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setPlayer2Name(newValue)
        }
    }

    fun updatePlayer1Wins(newValue: Int) {
        _player1Wins.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setPlayer1Wins(newValue)
        }
    }

    fun updatePlayer2Wins(newValue: Int) {
        _player2Wins.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setPlayer2Wins(newValue)
        }
    }

    fun updateCards(newValue: List<Boolean>) {
        _cards.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setCards(newValue)
        }
    }

    fun updateIsCardClicked(newValue: List<Boolean>) {
        _isCardClicked.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setIsCardClicked(newValue)
        }
    }

    fun updatePlay(newValue: Boolean) {
        _play.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setPlay(newValue)
        }
    }

    fun updateIsXImage(newValue: Boolean) {
        _isXImage.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setIsXImage(newValue)
        }
    }

    fun updateIs1Win(newValue: Boolean) {
        _is1Win.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setIs1Win(newValue)
        }
    }

    fun updateIsXFor1(newValue: Boolean) {
        _isXFor1.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setIsXFor1(newValue)
        }
    }

    fun updateIsPlayer1(newValue: Boolean) {
        _isPlayer1.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setIsPlayer1(newValue)
        }
    }

    fun updatePlayer1InToast(newValue: Boolean) {
        _player1InToast.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setPlayer1InToast(newValue)
        }
    }

    fun updateIsScoreUp(newValue: Boolean) {
        _isScoreUp.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setIsScoreUp(newValue)
        }
    }

    fun updateExpandButton(newValue: Boolean) {
        _expandButton.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setExpandButton(newValue)
        }
    }

    fun updateShowRestartButton(newValue: Boolean) {
        _showRestartButton.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setShowRestartButton(newValue)
        }
    }

    fun updateIsWinSoundPlaying(newValue: Boolean) {
        _isWinSoundPlaying.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setIsWinSoundPlaying(newValue)
        }
    }

    fun updateIsGotOldSoundPlaying(newValue: Boolean) {
        _isGotOldSoundPlaying.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setIsGotOldSoundPlaying(newValue)
        }
    }

    fun updateCoordinates(sX: Float, sY: Float, eX: Float, eY: Float) {     
        _startX.value = sX
        _startY.value = sY
        _endX.value = eX
        _endY.value = eY
        viewModelScope.launch(Dispatchers.IO) {
            repository.setCoordinates(sX, sY, eX, eY)
        }
    }

    fun updateWinner(newValue: Boolean) {
        _winner.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setWinner(newValue)
        }
    }

    fun updateGotOld(newValue: Boolean) {
        _gotOld.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            repository.setGotOld(newValue)
        }
    }

}