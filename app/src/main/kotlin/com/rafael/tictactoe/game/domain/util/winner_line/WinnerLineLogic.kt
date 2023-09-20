package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinatesTest
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun WinnerLineLogic(
    viewModel: GameViewModel,
    cordinate: WinnerLineCoordinatesTest
) {  
    val winningPositionsIndices = viewModel.winningPositionsIndices.value
   
    if (winningPositionsIndices.contains(0)) {
        cordinate.setFirstHorizontalLinePositions(viewModel)
    } else if (winningPositionsIndices.contains(1)) {
        cordinate.setSecondHorizontalLinePositions(viewModel)
    } else if (winningPositionsIndices.contains(2)) {
        cordinate.setTertiaryHorizontalLinePositions(viewModel)
    } else if (winningPositionsIndices.contains(3)) {
        cordinate.setFirstVerticalLinePositions(viewModel)
    } else if (winningPositionsIndices.contains(4)) {
        cordinate.setSecondVerticalLinePositions(viewModel)
    } else if (winningPositionsIndices.contains(5)) {
        cordinate.setTertiaryVerticalLinePositions(viewModel)
    } else if (winningPositionsIndices.contains(6)) {
        cordinate.setStartToEndTransversalLinePositions(viewModel)
    } else if (winningPositionsIndices.contains(7)) {
        cordinate.setEndToStartTransversalLinePositions(viewModel)
    }
}