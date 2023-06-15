package com.rafael.tictactoe.game.presentation.game_screen.viewmodels

import android.content.Context
import androidx.lifecycle.*
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.model.GameEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


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

	val initialCards = List(9) { false }
	private var _cards = MutableStateFlow(initialCards)
	val cards: StateFlow<List<Boolean>> = _cards
	val initialCardClicked = List(9) { false }
	private var _isCardClicked = MutableStateFlow(initialCardClicked)
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

    //GET DEFAUT VALUES FOR GAME VARIABLES FROM GAME ENTITY
    val gameDefaults = GameEntity.defaultGameValues()

	init {
		viewModelScope.launch(Dispatchers.IO) {
      	  val game = repository.getGameValues() ?: gameDefaults
            _currentPlayerName.value = game.currentPlayerName
            _player1Name.value = game.player1Name
            _player2Name.value = game.player2Name
            _player1Wins.value = game.player1Wins
            _player2Wins.value = game.player2Wins
            _cards.value = game.cards
            _isCardClicked.value = game.isCardClicked
            _play.value = game.play
            _isXImage.value = game.isXImage
            _is1Win.value = game.is1Win
            _isXFor1.value = game.isXFor1
            _isPlayer1.value = game.isPlayer1
            _player1InToast.value = game.player1InToast
            _isScoreUp.value = game.isScoreUp
            _expandButton.value = game.expandButton
            _showRestartButton.value = game.showRestartButton
            _isWinSoundPlaying.value = game.isWinSoundPlaying
            _isGotOldSoundPlaying.value = game.isGotOldSoundPlaying
            _startX.value = game.startX
            _startY.value = game.startY
            _endX.value = game.endX
            _endY.value = game.endY
            _winner.value = game.winner
            _gotOld.value = game.gotOld
        }
    }

    fun updateCurrentPlayerName(newValue: String) {
        _currentPlayerName.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
            game.currentPlayerName = newValue
            repository.setGameValues(game)
        }
    }

    fun updatePlayer1Name(newValue: String) {
        _player1Name.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.player1Name = newValue
            repository.setGameValues(game)
        }
    }

    fun updatePlayer2Name(newValue: String) {
        _player2Name.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.player2Name = newValue
            repository.setGameValues(game)
        }
    }

    fun updatePlayer1Wins(newValue: Int) {
        _player1Wins.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.player1Wins = newValue
            repository.setGameValues(game)
        }
    }

    fun updatePlayer2Wins(newValue: Int) {
        _player2Wins.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.player2Wins = newValue
            repository.setGameValues(game)
        }
    }

	fun updateCard(index: Int, value: Boolean) {
	    val currentCards = _cards.value.toMutableList()
	    currentCards[index] = value
	    _cards.value = currentCards
	}

	fun updateIsCardClicked(index: Int, value: Boolean) {
 	   val currentIsCardClicked = _isCardClicked.value.toMutableList()
 	   currentIsCardClicked[index] = value
 	   _isCardClicked.value = currentIsCardClicked
	}

    fun updateAllCards(newCardsValue: List<Boolean>) {
        _cards.value = newCardsValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			//game. = newValue
            repository.setGameValues(game)
            repository.setCards(newCardsValue)
        }
    }

    fun updateAllIsCardClicked(newCardClickedValue: List<Boolean>) {
        _isCardClicked.value = newCardClickedValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			//game. = newValue
            repository.setGameValues(game)
            repository.setIsCardClicked(newCardClickedValue)
        }
    }

    fun updatePlay(newValue: Boolean) {
        _play.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.play = newValue
            repository.setGameValues(game)
        }
    }

    fun updateIsXImage(newValue: Boolean) {
        _isXImage.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.isXImage = newValue
            repository.setGameValues(game)
        }
    }

    fun updateIs1Win(newValue: Boolean) {
        _is1Win.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.is1Win = newValue
            repository.setGameValues(game)
        }
    }

    fun updateIsXFor1(newValue: Boolean) {
        _isXFor1.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.isXFor1 = newValue
            repository.setGameValues(game)
        }
    }

    fun updateIsPlayer1(newValue: Boolean) {
        _isPlayer1.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.isPlayer1 = newValue
            repository.setGameValues(game)
        }
    }

    fun updatePlayer1InToast(newValue: Boolean) {
        _player1InToast.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.player1InToast = newValue
            repository.setGameValues(game)
        }
    }

    fun updateIsScoreUp(newValue: Boolean) {
        _isScoreUp.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.isScoreUp = newValue
            repository.setGameValues(game)
        }
    }

    fun updateExpandButton(newValue: Boolean) {
        _expandButton.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.expandButton = newValue
            repository.setGameValues(game)
        }
    }

    fun updateShowRestartButton(newValue: Boolean) {
        _showRestartButton.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.showRestartButton = newValue
            repository.setGameValues(game)
        }
    }

    fun updateIsWinSoundPlaying(newValue: Boolean) {
        _isWinSoundPlaying.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.isWinSoundPlaying = newValue
            repository.setGameValues(game)
        }
    }

    fun updateIsGotOldSoundPlaying(newValue: Boolean) {
        _isGotOldSoundPlaying.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.isGotOldSoundPlaying = newValue
            repository.setGameValues(game)
        }
    }

    fun updateCoordinates(sX: Float, sY: Float, eX: Float, eY: Float) {     
        _startX.value = sX
        _startY.value = sY
        _endX.value = eX
        _endY.value = eY
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
            game.startX = sX
            game.startY = sY
			game.endX = eX
			game.endY = eY
            repository.setGameValues(game)
        }
    }

    fun updateWinner(newValue: Boolean) {
        _winner.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.winner = newValue
            repository.setGameValues(game)
        }
    }

    fun updateGotOld(newValue: Boolean) {
        _gotOld.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
			val game = repository.getGameValues() ?: gameDefaults
			game.gotOld = newValue
            repository.setGameValues(game)
        }
    }
}