package com.rafael.tictactoe.feature.game.domain.model

sealed interface RequestState {
    data object Success : RequestState
    data class Failure(val type: Error = Error.IOError, val message: String = "", val code: Int = -1) : RequestState
    data object Loading : RequestState
}

sealed interface Error {
    data object IOError : Error
    data object HttpError : Error
}
