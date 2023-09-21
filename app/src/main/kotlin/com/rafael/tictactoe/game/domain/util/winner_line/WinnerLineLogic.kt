package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun WinnerLineLogic(
    viewModel: GameViewModel,
    coordinate: WinnerLineCoordinates
) {  
    val winningPositionsIndices = viewModel.winningPositionsIndices.value
   
    if (winningPositionsIndices.contains(0)) {
        coordinate.setFirstHorizontalLinePositions(viewModel)
    } else if (winningPositionsIndices.contains(1)) {
        coordinate.setSecondHorizontalLinePositions(viewModel)
    } else if (winningPositionsIndices.contains(2)) {
        coordinate.setTertiaryHorizontalLinePositions(viewModel)
    } else if (winningPositionsIndices.contains(3)) {
        coordinate.setFirstVerticalLinePositions(viewModel)
    } else if (winningPositionsIndices.contains(4)) {
        coordinate.setSecondVerticalLinePositions(viewModel)
    } else if (winningPositionsIndices.contains(5)) {
        coordinate.setTertiaryVerticalLinePositions(viewModel)
    } else if (winningPositionsIndices.contains(6)) {
        coordinate.setStartToEndTransversalLinePositions(viewModel)
    } else if (winningPositionsIndices.contains(7)) {
        coordinate.setEndToStartTransversalLinePositions(viewModel)
    }
}