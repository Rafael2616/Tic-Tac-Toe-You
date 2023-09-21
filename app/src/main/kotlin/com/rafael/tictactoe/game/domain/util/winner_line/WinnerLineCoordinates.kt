package com.rafael.tictactoe.game.domain.util

import android.app.Activity
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
 
class WinnerLineCoordinates(activity: Activity) : Coordinates(activity) {
      
    fun setFirstHorizontalLinePositions(viewModel: GameViewModel) {
        val startX = horizontalStartX
        val startY = heightCenter
        val endX = horizontalEndX
        val endY = heightCenter
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setSecondHorizontalLinePositions(viewModel: GameViewModel) {
        val startX = horizontalStartX
        val startY = secondHorizontalY
        val endX = horizontalEndX
        val endY = secondHorizontalY
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setTertiaryHorizontalLinePositions(viewModel: GameViewModel) {
        val startX = horizontalStartX
        val startY = tertiaryHorizontalY
        val endX = horizontalEndX
        val endY = tertiaryHorizontalY
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setFirstVerticalLinePositions(viewModel: GameViewModel) {
    	val startX = firstVerticalStartAndEndX
        val startY = verticalAndTransversalStartY
        val endX = firstVerticalStartAndEndX
        val endY = endHeight
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setSecondVerticalLinePositions(viewModel: GameViewModel) {
        val startX = widthCenter
        val startY = verticalAndTransversalStartY
        val endX = widthCenter
        val endY = endHeight
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setTertiaryVerticalLinePositions(viewModel: GameViewModel) {
        val startX = tertiaryVerticalStartAndEndX
        val startY = verticalAndTransversalStartY
        val endX = tertiaryVerticalStartAndEndX
        val endY = endHeight
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setStartToEndTransversalLinePositions(viewModel: GameViewModel) {
        val startX = horizontalStartX
        val startY = verticalAndTransversalStartY
        val endX = horizontalEndX
        val endY = transversalEndY
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setEndToStartTransversalLinePositions(viewModel: GameViewModel) {
        val startX = horizontalEndX
        val startY = verticalAndTransversalStartY
        val endX = horizontalStartX
        val endY = transversalEndY
		viewModel.setCoordinates(startX, startY, endX, endY)
    }
}