package com.rafael.tictactoe.game.domain.util

<<<<<<< HEAD
import android.content.Context
import com.rafael.tictactoe.game.domain.util.GotOldSound
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun GotOldChecker(
    vm: GameViewModel,
    settingsVM: SettingsViewModel,
    context: Context,
) {
=======
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun GotOldChecker(vm: GameViewModel) {
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
    val gotOld = vm.gotOld.value
    val winner = vm.winner.value
    val isCardClicked = vm.isCardClicked.value

	if ((isCardClicked.flatten().all { it }) && (!winner)) {
		vm.updateGotOld(true)
    } else {
		vm.updateGotOld(false)
	}
    
    if (gotOld) {
        vm.updateIsGameFinished(true)
<<<<<<< HEAD
        GotOldSound(context, vm, settingsVM)
=======
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
    }
}