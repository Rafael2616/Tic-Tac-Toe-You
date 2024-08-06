package com.rafael.tictactoe.feature.game.data.repository

import com.rafael.tictactoe.core.platform.network.utils.Error
import com.rafael.tictactoe.core.platform.network.utils.NetworkError
import com.rafael.tictactoe.core.platform.network.utils.Result
import com.rafael.tictactoe.core.platform.network.utils.asEmptyDataResult
import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.game.domain.model.OnlineGameState
import com.rafael.tictactoe.feature.game.domain.model.Player
import com.rafael.tictactoe.feature.game.domain.repository.ErrorMessage
import com.rafael.tictactoe.feature.game.domain.repository.GameRepository
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.SerializationException

internal class GameRepositoryImpl(
    private val httpClient: HttpClient,
) : GameRepository {

    init {
        println("Game repository has been initialized")
    }

    override suspend fun getRemotePlayers(): Pair<Result<List<Player>, Error>, ErrorMessage> {
        val response = try {
            httpClient.get(urlString = "https://retoolapi.dev/IdekO9/players")
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET) to e.stackTraceToString()
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION) to e.stackTraceToString()
        } catch (e: Exception) {
            return Result.Error(NetworkError.UNKNOWN) to e.stackTraceToString()
        }
        return if (response.status.value in 200..299) {
            val players = response.body<List<Player>>()
            Result.Success(players) to ""
        } else {
            handleResponse(response)
        }
    }

    override suspend fun postPlayer(
        state: GameState,
        settingsState: SettingsState,
    ): Pair<Result<Unit, Error>, ErrorMessage> {
        val response = try {
            httpClient.post(urlString = "https://retoolapi.dev/IdekO9/players/${state.matchID}") {
                contentType(ContentType.Application.Json)
                setBody(
                    Player(
                        id = state.matchID,
                        name = if (state.localPlayer1) state.player1Name else state.player2Name,
                        color = if (state.isXFor1) settingsState.selectedXColor else settingsState.selectedCircleColor,
                        isXFor1 = state.isXFor1,
                        isPlayer1 = state.isYouPlayer1,
                    ),
                )
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET) to e.stackTraceToString()
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION) to e.stackTraceToString()
        } catch (e: Exception) {
            return Result.Error(NetworkError.UNKNOWN) to e.stackTraceToString()
        }
        return if (response.status.value in 200..299) {
            Result.Success(Unit).asEmptyDataResult() to ""
        } else {
            handleResponse(response)
        }
    }

    override suspend fun removePlayer(matchID: Int): Pair<Result<Unit, Error>, ErrorMessage> {
        val response = try {
            httpClient.delete(urlString = "https://retoolapi.dev/IdekO9/players/$matchID")
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET) to e.stackTraceToString()
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION) to e.stackTraceToString()
        } catch (e: Exception) {
            return Result.Error(NetworkError.UNKNOWN) to e.stackTraceToString()
        }
        return if (response.status.value in 200..299) {
            Result.Success(Unit).asEmptyDataResult() to ""
        } else {
            handleResponse(response)
        }
    }

    override suspend fun postInitialGameState(matchID: Int): Pair<Result<Unit, Error>, ErrorMessage> {
        val response = try {
            httpClient.post(urlString = "https://retoolapi.dev/IdekO9/game_state/$matchID") {
                contentType(ContentType.Application.Json)
                setBody(
                    OnlineGameState(
                        matchID = matchID,
                        player1Wins = 0,
                        player2Wins = 0,
                        card = List(3) { List(3) { false } },
                        isCardClicked = List(3) { List(3) { false } },
                        play = false,
                        isXFor1 = true,
                        isPlayer1 = true,
                        hasWinner = false,
                        gotOld = false,
                        isGameFinished = false,
                    ),
                )
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET) to e.stackTraceToString()
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION) to e.stackTraceToString()
        } catch (e: Exception) {
            return Result.Error(NetworkError.UNKNOWN) to e.stackTraceToString()
        }
        return if (response.status.value in 200..299) {
            Result.Success(Unit).asEmptyDataResult() to ""
        } else {
            handleResponse(response)
        }
    }

    override suspend fun getGameState(): Pair<Result<Flow<OnlineGameState>, Error>, ErrorMessage> {
        val response = try {
            httpClient.get(urlString = "https://retoolapi.dev/IdekO9/game_state/")
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET) to e.stackTraceToString()
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION) to e.stackTraceToString()
        } catch (e: Exception) {
            return Result.Error(NetworkError.UNKNOWN) to e.stackTraceToString()
        }
        return if (response.status.value in 200..299) {
            val gameState = response.body<Flow<OnlineGameState>>()
            Result.Success(gameState) to ""
        } else {
            handleResponse(response)
        }
    }

    override suspend fun updateGameState(state: GameState): Pair<Result<Flow<OnlineGameState>, Error>, ErrorMessage> {
        val response = try {
            httpClient.post(urlString = "https://retoolapi.dev/IdekO9/game_state/{${state.matchID}}") {
                contentType(ContentType.Application.Json)
                setBody(
                    OnlineGameState(
                        matchID = state.matchID,
                        player1Wins = state.player1Wins,
                        player2Wins = state.player2Wins,
                        card = state.card,
                        isCardClicked = state.isCardClicked,
                        play = state.play,
                        isXFor1 = state.isXFor1,
                        isPlayer1 = state.isPlayer1,
                        hasWinner = state.hasWinner,
                        gotOld = state.gotOld,
                        isGameFinished = state.isGameFinished,
                    ),
                )
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET) to e.stackTraceToString()
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION) to e.stackTraceToString()
        } catch (e: Exception) {
            return Result.Error(NetworkError.UNKNOWN) to e.stackTraceToString()
        }
        return if (response.status.value in 200..299) {
            val gameState = response.body<Flow<OnlineGameState>>()
            Result.Success(gameState) to ""
        } else {
            handleResponse(response)
        }
    }

    override suspend fun endMatch(matchID: Int): Pair<Result<Unit, Error>, ErrorMessage> {
        val response = try {
            httpClient.delete(urlString = "https://retoolapi.dev/IdekO9/game_state/$matchID")
            httpClient.delete(urlString = "https://retoolapi.dev/IdekO9/players/$matchID")
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET) to e.stackTraceToString()
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION) to e.stackTraceToString()
        }
        return if (response.status.value in 200..299) {
            Result.Success(Unit).asEmptyDataResult() to ""
        } else {
            handleResponse(response)
        }
    }
}

private fun handleResponse(response: HttpResponse) = when (response.status.value) {
    401 -> Result.Error(NetworkError.UNAUTHORIZED) to response.status.toString()
    409 -> Result.Error(NetworkError.CONFLICT) to response.status.toString()
    408 -> Result.Error(NetworkError.REQUEST_TIMEOUT) to response.status.toString()
    413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE) to response.status.toString()
    in 500..599 -> Result.Error(NetworkError.SERVER_ERROR) to response.status.toString()
    else -> Result.Error(NetworkError.UNKNOWN) to response.status.toString()
}
