package com.rafael.tictactoe.game.domain.util

import android.content.Context
import com.rafael.tictactoe.game.domain.util.WinnerSound
import com.rafael.tictactoe.game.domain.util.VictoryCounterUpdater
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun WinnerChecker(
    vm: GameViewModel,
    settingsVM: SettingsViewModel,
    context: Context,
    card: List<List<Boolean>>,
    isCardClicked: List<List<Boolean>>
) {
    val isXFor1 = vm.isXFor1.value

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

    for (positions in winningPositions) {
        val areAllClicked = positions.all { (x, y) -> isCardClicked[x][y] }
        val areAllX = positions.all { (x, y) -> card[x][y] }
        val areAllO = positions.all { (x, y) -> !card[x][y] }

        if (areAllClicked) {
            if (areAllX) {
                vm.updateWinner(true)
                if (isXFor1) {
                    vm.updateIs1Win(true)
                } else {
                    vm.updateIs1Win(false)
                }   
            } else if (areAllO) {
                vm.updateWinner(true)
                if (isXFor1) {
                    vm.updateIs1Win(false)
                } else {
                    vm.updateIs1Win(true)
                }   
            }
        }
    }

    val winner = vm.winner.value

    if (winner) {
        vm.updateIsGameFinished(true)
        VictoryCounterUpdater(vm)
        WinnerSound(context, vm, settingsVM)
    }
}