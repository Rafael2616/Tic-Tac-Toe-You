package com.rafael.tictactoe.game.data.repository

import android.util.Log
import com.rafael.tictactoe.game.data.dao.GameDao
import com.rafael.tictactoe.game.data.dao.OnlineGameDao
import com.rafael.tictactoe.game.domain.model.Error
import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.model.Player
import com.rafael.tictactoe.game.domain.model.RequestState
import com.rafael.tictactoe.game.domain.repository.GameRepository
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
}