package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

// THIS COORDINATES ONLY WORKS AS EXPECTED IN DEVICES WITH HD SCREEN (1280/720) AND NEEDS REWORK!!

class WinnerLineCoordinates() {

    fun setFirstHorizontalLinePositions(viewModel: GameViewModel) {
        val startX = 15f
        val startY = 725f
        val endX = 705f
        val endY = 725f
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setSecondHorizontalLinePositions(viewModel: GameViewModel) {
        val startX = 15f
        val startY = 950f
        val endX = 705f
        val endY = 950f
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setTertiaryHorizontalLinePositions(viewModel: GameViewModel) {
        val startX = 15f
        val startY = 1170f
        val endX = 705f
        val endY = 1170f
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setFirstVerticalLinePositions(viewModel: GameViewModel) {
    	val startX = 125f
        val startY = 610f
        val endX = 125f
        val endY = 1285f
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setSecondVerticalLinePositions(viewModel: GameViewModel) {
        val startX = 362f
        val startY = 610f
        val endX = 362f
        val endY = 1285f
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setTertiaryVerticalLinePositions(viewModel: GameViewModel) {
        val startX = 595f
        val startY = 610f
        val endX = 595f
        val endY = 1285f
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setStartToEndTransversalLinePositions(viewModel: GameViewModel) {
        val startX = 15f
        val startY = 620f
        val endX = 705f
        val endY = 1275f
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setEndToStartTransversalLinePositions(viewModel: GameViewModel) {
        val startX = 705f
        val startY = 620f
        val endX = 15f
        val endY = 1275f
		viewModel.setCoordinates(startX, startY, endX, endY)
    }
}