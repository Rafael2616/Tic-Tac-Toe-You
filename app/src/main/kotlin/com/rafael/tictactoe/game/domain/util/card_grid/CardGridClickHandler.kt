package com.rafael.tictactoe.game.domain.util

import android.content.Context
import com.rafael.tictactoe.game.domain.util.CardGridSound
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun CardGridClickHandler(
    context: Context,
	vm: GameViewModel,
    settingsVM: SettingsViewModel,
	index: Int
) {
	val play = vm.play.value
    val winner = vm.winner.value
    val isCardClicked = vm.isCardClicked.value
    val isXImage = vm.isXImage.value
    val isPlayer1 = vm.isPlayer1.value
    val isTimerOn = vm.isTimerOn.value

    if(!isTimerOn) {
	    if ((play) && (!winner)) {
      	  if (!isCardClicked[index]) {
   	         if (isXImage) {
		    		vm.updateIsXImage(false)
		    		vm.updateIsPlayer1(!isPlayer1)
	                vm.updateCard(index, true)
		    		vm.updateIsCardClicked(index, true)
          	  } else {
    	            vm.updateIsXImage(true)
	    			vm.updateIsPlayer1(!isPlayer1)
    	            vm.updateCard(index, false)
		    		vm.updateIsCardClicked(index, true)
           	 }
                CardGridSound(context, vm, settingsVM)
        	}
    	}
    }
}
