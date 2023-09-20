package com.rafael.tictactoe.game.domain.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.rafael.tictactoe.game.domain.util.WinnerSound
import com.rafael.tictactoe.game.domain.util.VictoryCounterUpdater
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun WinnerChecker(
    vm: GameViewModel,
    settingsVM: SettingsViewModel,
    context: Context
) {
    val card by vm.card.collectAsState()
    val isCardClicked by vm.isCardClicked.collectAsState()
    val isXFor1 by vm.isXFor1.collectAsState()

    val winningPositions = listOf(
        // Lines
        listOf(Pair(0, 0), Pair(0, 1), Pair(0, 2)),
        listOf(Pair(1, 0), Pair(1, 1), Pair(1, 2)),
        listOf(Pair(2, 0), Pair(2, 1), Pair(2, 2)),

        // Columns
        listOf(Pair(0, 0), Pair(1, 0), Pair(2, 0)),
        listOf(Pair(0, 1), Pair(1, 1), Pair(2, 1)),
        listOf(Pair(0, 2), Pair(1, 2), Pair(2, 2)),

        // Diagonals
        listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2)),
        listOf(Pair(0, 2), Pair(1, 1), Pair(2, 0))
    )

    for ((index, positions) in winningPositions.withIndex()) {
        val areAllClicked = positions.all { (x, y) -> isCardClicked[x][y] }
        val areAllClickedByPlayer1 = positions.all { (x, y) -> card[x][y] }
        val areAllClickedByPlayer2 = positions.all { (x, y) -> !card[x][y] }

        if (areAllClicked) {
            if (areAllClickedByPlayer1) {
                vm.updateWinner(true)
                if (isXFor1) {
                    vm.updateIs1Win(true)
                } else {
                    vm.updateIs1Win(false)
                }
                vm.updateWinningPositionsIndices(index)
            } else if (areAllClickedByPlayer2) {
                vm.updateWinner(true)
                if (isXFor1) {
                    vm.updateIs1Win(false)
                } else {
                    vm.updateIs1Win(true)
                }
                vm.updateWinningPositionsIndices(index)
            }
        }
    }
    
    val hasWinner by vm.winner.collectAsState()

    if (hasWinner) {
        vm.updateIsGameFinished(true)
        VictoryCounterUpdater(vm)
        WinnerSound(context, vm, settingsVM)
    }
}