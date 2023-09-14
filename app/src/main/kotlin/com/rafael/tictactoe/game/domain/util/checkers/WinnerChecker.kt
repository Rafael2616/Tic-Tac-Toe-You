package com.rafael.tictactoe.game.domain.util

<<<<<<< HEAD
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
=======
import com.rafael.tictactoe.game.domain.util.VictoryCounterUpdater
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun WinnerChecker(
    vm: GameViewModel,
    card: List<List<Boolean>>,
    isCardClicked: List<List<Boolean>>
) {   
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
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
               vm.updateIs1Win(true)
               vm.updateWinner(true)
            } else if (areAllO) {             
               vm.updateIs1Win(false)
               vm.updateWinner(true)
            }
        }
    }

    val winner = vm.winner.value

    if (winner) {
        vm.updateIsGameFinished(true)
        VictoryCounterUpdater(vm)
<<<<<<< HEAD
        WinnerSound(context, vm, settingsVM)
    }
}
=======
    }
}
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
