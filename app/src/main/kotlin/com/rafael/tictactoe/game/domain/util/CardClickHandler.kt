package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun CardClickHandler(
vm: GameViewModel,
isCardClicked: List<Boolean>,
index: Int,
play: Boolean,
isXImage: Boolean,
winner: Boolean,
isPlayer1: Boolean
) {	
	if ((play) && (!winner)) {
  	  if (!isCardClicked[index]) {
   	     if (isXImage) {
				vm.updateIsXImage(false)
				vm.updateIsPlayer1(!isPlayer1)
	            vm.updateCard(index,true)
				vm.updateIsCardClicked(index,true)
      	  } else {
    	        vm.updateIsXImage(true)
				vm.updateIsPlayer1(!isPlayer1)
    	        vm.updateCard(index,true)
				vm.updateIsCardClicked(index,true)
       	 }
    	}
	}
}