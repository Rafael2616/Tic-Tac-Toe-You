package com.rafael.tictactoe.game.domain.util

import android.app.Activity
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.common.domain.util.ScreenSizeGetter

class WinnerLineCoordinatesTest(val activity: Activity) {

	val screenSize = ScreenSizeGetter(activity)
	val width = screenSize.first
	val height = screenSize.second
    
	val widthCenter = width / 2
	val heightCenter = height / 2
    
	val widthTenPercent = width / 10
	val heightTenPercent = height / 10
    
    val widthOnePercent = width / 100
    val heightOnePercent = height / 100

    val widthTwoPercent = width / 50
    val heightTwoPercent = height / 50

    val widthFivePercent = width / 20
    val heightFivePercent = height / 20

	val horizontalStart = widthTenPercent / 5 //Ok
	val horizontalEnd = width - (widthTenPercent / 5) //Ok

    val secondHorizontalY = (heightCenter + (heightTenPercent + heightFivePercent + heightOnePercent))
    val tertiaryHorizontalY = (secondHorizontalY + heightTenPercent + heightFivePercent + heightOnePercent)

    fun setFirstHorizontalLinePositions(viewModel: GameViewModel) {
        val startX = horizontalStart.toFloat() //Ok
        val startY = heightCenter.toFloat() //Ok + review
        val endX = horizontalEnd.toFloat() //Ok
        val endY = heightCenter.toFloat() //ok + review
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setSecondHorizontalLinePositions(viewModel: GameViewModel) {
        val startX = horizontalStart.toFloat() //Ok
        val startY = secondHorizontalY.toFloat() //Ok
        val endX = horizontalEnd.toFloat() //Ok
        val endY = secondHorizontalY.toFloat() // Ok
		viewModel.setCoordinates(startX, startY, endX, endY)
    }

    fun setTertiaryHorizontalLinePositions(viewModel: GameViewModel) {
        val startX = horizontalStart.toFloat() //Ok
        val startY = tertiaryHorizontalY.toFloat()
        val endX = horizontalEnd.toFloat() //Ok
        val endY = tertiaryHorizontalY.toFloat()
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