package com.rafael.tictactoe.feature.game.domain.model

internal sealed interface RequestState {
    object Success : RequestState
    data class Failure(val type: Error = Error.IOError, val message: String = "", val code: Int = -1) : RequestState
    object Loading : RequestState
}

internal sealed interface Error {
    object IOError : Error
    object HttpError : Error
}
