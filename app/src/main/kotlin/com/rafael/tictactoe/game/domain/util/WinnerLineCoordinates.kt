package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository
import javax.inject.Inject

//THIS COORDINATES ONLY WORKS AS EXPECTED IN DEVICES WITH HD SCREEN(1280/720) AND NEEDS REWORK!!

class WinnerLineCoordinates @Inject constructor(
private val repository : GameRepository
) {

suspend fun setFirstHorizontalLinePositions() {
	repository.setCoordinates(15f, 725f, 705f, 725f)
}

suspend fun setSecondHorizontalLinePositions() {
	repository.setCoordinates(15f, 950f, 705f, 950f)
}

suspend fun setTertiaryHorizontalLinePositions() {
	repository.setCoordinates(15f, 1170f, 705f, 1170f)
}

suspend fun setFirstVerticalLinePositions() {
	repository.setCoordinates(125f, 610f, 125f, 1285f)
}

suspend fun setSecondVerticalLinePositions() {
    repository.setCoordinates(362f, 610f, 362f, 1285f)
}

suspend fun setTertiaryVerticalLinePositions() {
    repository.setCoordinates(595f, 610f, 595f, 1285f)
}

suspend fun setStartToEndTransversalLinePositions() {
    repository.setCoordinates(15f, 620f, 705f, 1275f)
}

suspend fun setEndToStartTransversalLinePositions() {
    repository.setCoordinates(705f, 620f, 15f, 1275f)
    }
}