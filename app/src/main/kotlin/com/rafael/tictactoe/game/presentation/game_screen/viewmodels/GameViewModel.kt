package com.rafael.tictactoe.game.presentation.game_screen.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.game.data.dao.OnlineGameDao
import com.rafael.tictactoe.game.domain.audio.CardGridSound
import com.rafael.tictactoe.game.domain.audio.GotOldSound
import com.rafael.tictactoe.game.domain.audio.WinnerSound
import com.rafael.tictactoe.game.domain.card_grid.boardMovesHandler
import com.rafael.tictactoe.game.domain.card_grid.cpuMoveLevelsHandler
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.model.Error
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.domain.model.OnlineGameState
import com.rafael.tictactoe.game.domain.model.Player
import com.rafael.tictactoe.game.domain.model.RequestState
import com.rafael.tictactoe.game.domain.restart_game.RestartGame
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

@HiltViewModel
class GameViewModel @Inject constructor(
    private val player: ExoPlayer,
    private val settingsVM: SettingsViewModel,
    private val context: Application,
    private val repository: GameRepository,
    private val retrofit: Retrofit
) : ViewModel() {

    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()
    val gameService = retrofit.create(OnlineGameDao::class.java)
    var availablePlayers: Flow<List<Player>> = flow { emit(emptyList()) }
    var onlineGameState: Flow<OnlineGameState>? = null
   //val secondPlayer: Flow<Player> = availablePlayers.first()

    fun onEvent(event: GameEvent) {
        val localPlayer1  = state.value.localPlayer1
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
                _state.update { it.copy(
                    isXImage = it.isXFor1,
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
                if (state.value.gameMode != GameMode.Online) {
                    _state.update { it.copy(
                        isTimerOn = true,
                        play = true
                    )}
                } else {
                    _state.update { it.copy(showGetPrefsDialog = false)}
                    this@GameViewModel.onEvent(GameEvent.UpdateIsYouPlayer1)
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
            GameEvent.GetRemotePlayers -> {
	            viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val response = gameService.getPlayers()
                        delay(2000L)
                        if (response.isSuccessful && response.body() != null) {
                            availablePlayers = response.body()!!
                            _state.update { it.copy(
                                requestState = RequestState.Success,
                                isTimerOn = true,
                                play = true
                            )}
                        } else {
                            if (!response.isSuccessful) {
                               val message = "Unable to connect to the server"
                                Log.e("GameViewModel", message)
                                _state.update { it.copy(requestState = RequestState.Failure(Error.UnableToConnect, message)) }
                            }else if (response.body() == null) {
                                val message = "No players avaliable in server"
                                Log.e("GameViewModel", message)
                                _state.update { it.copy(requestState = RequestState.Failure(Error.NoPlayers, message)) }                            
                            }           
                        }
                    } catch(e: IOException) {
                         Log.e("GameViewModel", "IO error: $e")
                         _state.update { it.copy(requestState = RequestState.Failure(Error.IOError, e.toString())) }
                    } catch(e: HttpException) {
                         Log.e("GameViewModel", "HTTP error: ${e.code()}, ${e.message()}")
                         _state.update { it.copy(requestState = RequestState.Failure(Error.HttpError, e.message(), e.code())) }
                    }
                }
            }
            GameEvent.PostRemotePlayer -> {
	            viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val response = gameService.postPlayer(
                            Player(
                                id = 1,
                                name = if (state.value.localPlayer1) {
                                    state.value.player1Name
                                } else state.value.player2Name,
                                color = if (state.value.isXFor1) {
                                    settingsVM.state.value.xColor
                                } else settingsVM.state.value.circleColor,
                                    isXImage = state.value.isXImage
                            )
                        )
                        if (response.isSuccessful) {
                            _state.update { it.copy(requestState = RequestState.Success) }
                        } else {
                            Log.e("GameViewModel", "Unable to post player in server")
                            _state.update { it.copy(requestState = RequestState.Failure(Error.NoPlayers)) }
                        }
                    } catch(e: IOException) {
                         Log.e("GameViewModel", "IO error: $e")
                         _state.update { it.copy(requestState = RequestState.Failure(Error.IOError)) }
                    } catch(e: HttpException) {
                         Log.e("GameViewModel", "HTTP error: ${e.code()}, ${e.message()}")
                         _state.update { it.copy(requestState = RequestState.Failure(Error.HttpError)) }
                    }
                }
            }
            GameEvent.RemoveRemotePlayer -> {
    	        viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val response = gameService.removePlayer(state.value.playerID)
                        if (response.isSuccessful) {
                            _state.update { it.copy(requestState = RequestState.Success) }
                        } else {
                            Log.e("GameViewModel", "Unable to remove player from server")
                            _state.update { it.copy(requestState = RequestState.Failure(Error.NoPlayers)) }
                        }
                    } catch(e: IOException) {
                         Log.e("GameViewModel", "IO error: $e")
                         _state.update { it.copy(requestState = RequestState.Failure(Error.IOError)) }
                    } catch(e: HttpException) {
                         Log.e("GameViewModel", "HTTP error: ${e.code()}, ${e.message()}")
                         _state.update { it.copy(requestState = RequestState.Failure(Error.HttpError)) }
                    }
                }
            }
            GameEvent.SendInitialOnlineGameState -> {
      	      viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val response = gameService.postInitialGameState(
                            OnlineGameState(
                                player1Wins = 0,
                            	player2Wins = 0,
                            	card = List(3){ List<Boolean>(3){false} },
                            	isCardClicked = List(3){ List<Boolean>(3){false} },
                                play = false,
                            	isXImage = true,
                            	is1Win = false,
                            	isXFor1 = true,
                            	isPlayer1 = true,
                            	hasWinner = false,
                            	gotOld = false,
                            	isGameFinished = false
                            )
                        )
                        if (response.isSuccessful) {
                            _state.update { it.copy(requestState = RequestState.Success) }
                        } else {
                            Log.e("GameViewModel", "Unable to remove player from server")
                            _state.update { it.copy(requestState = RequestState.Failure(Error.NoPlayers)) }
                        }
                    } catch(e: IOException) {
                         Log.e("GameViewModel", "IO error: $e")
                         _state.update { it.copy(requestState = RequestState.Failure(Error.IOError)) }
                    } catch(e: HttpException) {
                         Log.e("GameViewModel", "HTTP error: ${e.code()}, ${e.message()}")
                         _state.update { it.copy(requestState = RequestState.Failure(Error.HttpError)) }
                    }
                }
            }
            GameEvent.GetOnlineGameState -> {
	            viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val response = gameService.getGameState()
                        if (response.isSuccessful && response.body() != null) {
                            onlineGameState = response.body()!!
                            _state.update { it.copy(
                                requestState = RequestState.Success,
                               /* player1Wins = onlineGameState.player1Wins.value,
                                player2Wins = onlineGameState.player2Wins.value,
                                card = onlineGameState.card.value,
                                isCardClicked = onlineGameState.isCardClicked.value,
                                play = onlineGameState.play.value,
                                isXImage = onlineGameState.isXImage.value,
                                isXFor1 = onlineGameState.isXFor1.value,
                                isPlayer1 = onlineGameState.isPlayer1.value,
                                hasWinner = onlineGameState.hasWinner.value,
                                gotOld = onlineGameState.gotOld.value,
                                isGameFinished = onlineGameState.isGameFinished.value*/
                            )}   
                        } else {
                            Log.e("GameViewModel", "Unable to get players from server")
                            _state.update { it.copy(requestState = RequestState.Failure(Error.NoPlayers)) }
                        }
                    } catch(e: IOException) {
                         Log.e("GameViewModel", "IO error: $e")
                         _state.update { it.copy(requestState = RequestState.Failure(Error.IOError)) }
                    } catch(e: HttpException) {
                         Log.e("GameViewModel", "HTTP error: ${e.code()}, ${e.message()}")
                         _state.update { it.copy(requestState = RequestState.Failure(Error.HttpError)) }
                    }
                }
            }
            GameEvent.UpdateOnlineGameState -> {
	            viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val response = gameService.updateGameState(
                            OnlineGameState(   
                                player1Wins = state.value.player1Wins,
                                player2Wins = state.value.player2Wins,
                                card = state.value.card,
                                isCardClicked = state.value.isCardClicked,
                                play = state.value.play,
                                isXImage = state.value.isXImage,
                                isXFor1 = state.value.isXFor1,
                                isPlayer1 = state.value.isPlayer1,
                                hasWinner = state.value.hasWinner,
                                gotOld = state.value.gotOld,
                                isGameFinished = state.value.isGameFinished
                            )                   
                        )
                        if (response.isSuccessful && response.body() != null) {
                            onlineGameState = response.body()!!
                            _state.update { it.copy(requestState = RequestState.Success) }
                          } else {
                            Log.e("GameViewModel", "Unable to get players from server")
                            _state.update { it.copy(requestState = RequestState.Failure(Error.NoPlayers)) }
                        }
                    } catch(e: IOException) {
                         Log.e("GameViewModel", "IO error: $e")
                         _state.update { it.copy(requestState = RequestState.Failure(Error.IOError)) }
                    } catch(e: HttpException) {
                         Log.e("GameViewModel", "HTTP error: ${e.code()}, ${e.message()}")
                         _state.update { it.copy(requestState = RequestState.Failure(Error.HttpError)) }
                    }
                }
            }
            GameEvent.EndOnlineMatch -> {
  	          viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val response = gameService.endMatch(state.value.matchID)
                        if (response.isSuccessful) {
                            _state.update { it.copy(requestState = RequestState.Success) }
                        } else {
                            Log.e("GameViewModel", "Unable to finish match in server")
                            _state.update { it.copy(requestState = RequestState.Failure(Error.NoPlayers)) }
                        }
                    } catch(e: IOException) {
                         Log.e("GameViewModel", "IO error: $e")
                         _state.update { it.copy(requestState = RequestState.Failure(Error.IOError)) }
                    } catch(e: HttpException) {
                         Log.e("GameViewModel", "HTTP error: ${e.code()}, ${e.message()}")
                         _state.update { it.copy(requestState = RequestState.Failure(Error.HttpError)) }
                    }
                }
            }
            is GameEvent.UpdateRequestState -> {
                _state.update { it.copy(requestState = event.state) }
            }
            GameEvent.UpdateIsYouPlayer1 -> {
                val isYouPlayer1 = (1..3).random()
                val value = if (isYouPlayer1 == 1) true else false

                _state.update { it.copy(
                    isYouPlayer1 = value,
                    localPlayer1 = value,
                    isPlayer1 = value
                )}
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

    fun updateIsOnlinePlayerTurn(newValue: Boolean) {
         _state.update { it.copy(isOnlinePlayerTurn = newValue)}
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