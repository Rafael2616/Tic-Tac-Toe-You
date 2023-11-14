package com.rafael.tictactoe.game.domain.model

sealed interface RequestState {
    object Success : RequestState
    data class Failure(val type: Error = Error.NoPlayers) : RequestState
    object Loading : RequestState
}

sealed interface Error {
    object NoPlayers : Error
    object IOError : Error
    object HttpError : Error
}