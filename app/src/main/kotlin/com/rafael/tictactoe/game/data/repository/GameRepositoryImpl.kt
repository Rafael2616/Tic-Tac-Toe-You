package com.rafael.tictactoe.game.data.repository

import android.util.Log
import com.rafael.tictactoe.game.data.dao.GameDao
import com.rafael.tictactoe.game.data.dao.OnlineGameDao
import com.rafael.tictactoe.game.domain.model.Error
import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.domain.model.OnlineGameState
import com.rafael.tictactoe.game.domain.model.Player
import com.rafael.tictactoe.game.domain.model.RequestState
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.settings.domain.model.SettingsState
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.HttpException

const val TAG = "GameRepository"

class GameRepositoryImpl @Inject constructor(
	private val gameDao: GameDao,
    private val gameService: OnlineGameDao
) : GameRepository {

	init {
		println("Game repository has been initialized")
	}
	
    override fun getGameValues(): Flow<GameEntity> {
        return gameDao.getGameValues()
    }
	
    override suspend fun setGameValues(gameEntity: GameEntity) {
        return gameDao.setGameValues(gameEntity)
    }

    override suspend fun getRemotePlayers(): Pair<Flow<List<Player>>?, RequestState> {
        try {
            val response = gameService.getPlayers()
            delay(2000L)
            if (response.isSuccessful && response.body() != null) {
                return Pair(response.body()!!, RequestState.Success)
            } else {
                if (!response.isSuccessful) {
                    throw IOException("Request failed with code: " + response.code() + response.message())
                } else if (response.body() == null) {
                    val message = " message: No players avaliable in server"
                    Log.e(TAG, message)
                    throw IOException("Request failed with code: " + response.code() + message)
                }
            }
        } catch(e: IOException) {
             Log.e(TAG, "IO error: $e")
             return Pair(null, RequestState.Failure(Error.IOError, e.toString()))
        } catch(e: HttpException) {
             Log.e(TAG, "HTTP error: ${e.code()}, ${e.message()}")
             return Pair(null, RequestState.Failure(Error.HttpError, e.message(), e.code()))
        }
        return Pair(null, RequestState.Loading)
    }

    override suspend fun postPlayer(state: GameState, settingsState: SettingsState): Pair<Player?, RequestState> {
        val randomicID = (1 until 99999).random()
            try {
                val response = gameService.postPlayer(
                Player(
                    id = randomicID,
                    name = if (state.localPlayer1) {
                        state.player1Name
                    } else state.player2Name,
                    color = if (state.isXFor1) {
                        settingsState.xColor
                    } else settingsState.circleColor,
                    isXImage = state.isXImage
                )
            )
            if (response.isSuccessful) {
                return Pair(response.body()!!, RequestState.Success)
            } else {
                throw IOException("Request failed with code: " + response.code())
            }
        } catch(e: IOException) {
             Log.e(TAG, "IO error: $e")
             return Pair(null, RequestState.Failure(Error.IOError, e.toString()))
        } catch(e: HttpException) {
             Log.e(TAG, "HTTP error: ${e.code()}, ${e.message()}")
             return Pair(null, RequestState.Failure(Error.HttpError, e.message(), e.code()))
        }
    }

    override suspend fun removePlayer(playerID: Int): RequestState {
        try {
            val response = gameService.removePlayer(playerID)
            if (response.isSuccessful) {
                 return RequestState.Success
            } else {
                throw IOException("Request failed with code: " + response.code())
            }
        } catch(e: IOException) {
             Log.e(TAG, "IO error: $e")
             return RequestState.Failure(Error.IOError, e.toString())
        } catch(e: HttpException) {
             Log.e(TAG, "HTTP error: ${e.code()}, ${e.message()}")
             return RequestState.Failure(Error.HttpError, e.message(), e.code())
        }
    }

    override suspend fun postInitialGameState(): RequestState {
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
                 return RequestState.Success
            } else {
                throw IOException("Request failed with code: " + response.code())
            }
        } catch(e: IOException) {
             Log.e(TAG, "IO error: $e")
             return RequestState.Failure(Error.IOError, e.toString())
        } catch(e: HttpException) {
             Log.e(TAG, "HTTP error: ${e.code()}, ${e.message()}")
             return RequestState.Failure(Error.HttpError, e.message(), e.code())
        }
    }

    override suspend fun getGameState() : Pair<Flow<OnlineGameState>?, RequestState> {
        try {
            val response = gameService.getGameState()
            if (response.isSuccessful && response.body() != null) {
                return Pair(response.body()!!, RequestState.Success)
            } else {
                throw IOException("Request failed with code: " + response.code())
            }
        } catch(e: IOException) {
             Log.e(TAG, "IO error: $e")
             return Pair(null, RequestState.Failure(Error.IOError, e.toString()))
        } catch(e: HttpException) {
             Log.e(TAG, "HTTP error: ${e.code()}, ${e.message()}")
             return Pair(null, RequestState.Failure(Error.HttpError, e.message(), e.code()))
        }
    }

    override suspend fun updateGameState(state: GameState): Pair<Flow<OnlineGameState>?, RequestState> {
        try {
            val response = gameService.updateGameState(
                OnlineGameState(   
                    player1Wins = state.player1Wins,
                    player2Wins = state.player2Wins,
                    card = state.card,
                    isCardClicked = state.isCardClicked,
                    play = state.play,
                    isXImage = state.isXImage,
                    isXFor1 = state.isXFor1,
                    isPlayer1 = state.isPlayer1,
                    hasWinner = state.hasWinner,
                    gotOld = state.gotOld,
                    isGameFinished = state.isGameFinished
                )                   
            )
            if (response.isSuccessful && response.body() != null) {
                return Pair(response.body()!!, RequestState.Success)
            } else {
                throw IOException("Request failed with code: " + response.code())
            }
        } catch(e: IOException) {
             Log.e(TAG, "IO error: $e")
             return Pair(null, RequestState.Failure(Error.IOError, e.toString()))
        } catch(e: HttpException) {
             Log.e(TAG, "HTTP error: ${e.code()}, ${e.message()}")
             return Pair(null, RequestState.Failure(Error.HttpError, e.message(), e.code()))
        }
    }

    override suspend fun endMatch(matchID: Int): RequestState {
       try {
            val response = gameService.endMatch(matchID)
            if (response.isSuccessful) {
                return RequestState.Success
            } else {
                throw IOException("Request failed with code: " + response.code())
            }
        } catch(e: IOException) {
             Log.e(TAG, "IO error: $e")
             return RequestState.Failure(Error.IOError, e.toString())
        } catch(e: HttpException) {
             Log.e(TAG, "HTTP error: ${e.code()}, ${e.message()}")
             return RequestState.Failure(Error.HttpError, e.message(), e.code())
        }
    }
}