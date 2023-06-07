package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*
import javax.inject.Inject


//THIS COORDINATES ONLY WORKS AS EXPECTED IN DEVICES WITH HD SCREEN(1280/720) AND NEEDS REWORK!!

class WinnerLineCoordinates @Inject constructor(
private val repository : GameRepository
) {

fun setFirstHorizontalLinePositions() = runBlocking {
	repository.setCoordinates(15f, 725f, 705f, 725f)
}

fun setSecondHorizontalLinePositions() = runBlocking {
	repository.setCoordinates(15f, 950f, 705f, 950f)
}

fun setTertiaryHorizontalLinePositions() = runBlocking {
	repository.setCoordinates(15f, 1170f, 705f, 1170f)
}

fun setFirstVerticalLinePositions() = runBlocking {
	repository.setCoordinates(125f, 610f, 125f, 1285f)
}

fun setSecondVerticalLinePositions() = runBlocking {
    repository.setCoordinates(362f, 610f, 362f, 1285f)
}

fun setTertiaryVerticalLinePositions() = runBlocking {
    repository.setCoordinates(595f, 610f, 595f, 1285f)
}

fun setStartToEndTransversalLinePositions() = runBlocking {
    repository.setCoordinates(15f, 620f, 705f, 1275f)
}

fun setEndToStartTransversalLinePositions() = runBlocking {
    repository.setCoordinates(705f, 620f, 15f, 1275f)
   }
}