package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinatesTest
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun WinnerLineLogic(
    viewModel: GameViewModel,
    cordinate: WinnerLineCoordinatesTest
) {  
    val cards = viewModel.cards.value
    val isCardClicked = viewModel.isCardClicked.value
    
	if (isCardClicked[0] && isCardClicked[1] && isCardClicked[2]){
		if ((cards[0] && cards[1] && cards[2]) || (!cards[0] && !cards[1] && !cards[2])) {
			cordinate.setFirstHorizontalLinePositions(viewModel)
		} 
	}
	if (isCardClicked[3] && isCardClicked[4] && isCardClicked[5]){
   	 if ((cards[3] && cards[4] && cards[5]) || (!cards[3] && !cards[4] && !cards[5])) {
     	   cordinate.setSecondHorizontalLinePositions(viewModel)
		} 
	}
	if (isCardClicked[6] && isCardClicked[7] && isCardClicked[8]){  
 	   if ((cards[6] && cards[7] && cards[8]) || (!cards[6] && !cards[7] && !cards[8])) {
			cordinate.setTertiaryHorizontalLinePositions(viewModel)
  	  }
	}
	if (isCardClicked[0] && isCardClicked[3] && isCardClicked[6]){
   	 if ((cards[0] && cards[3] && cards[6]) || (!cards[0] && !cards[3] && !cards[6])) {
			cordinate.setFirstVerticalLinePositions(viewModel)
    	}
	}
	if (isCardClicked[1] && isCardClicked[4] && isCardClicked[7]){ 
    	if ((cards[1] && cards[4] && cards[7]) || (!cards[1] && !cards[4] && !cards[7])) {
			cordinate.setSecondVerticalLinePositions(viewModel)
  	  }
	}
	if (isCardClicked[2] && isCardClicked[5] && isCardClicked[8]){
   	 if ((cards[2] && cards[5] && cards[8]) || (!cards[2] && !cards[5] && !cards[8])) {
			cordinate.setTertiaryVerticalLinePositions(viewModel)
    	}
	}
	if (isCardClicked[0] && isCardClicked[4] && isCardClicked[8]){
    	if ((cards[0] && cards[4] && cards[8]) || (!cards[0] && !cards[4] && !cards[8])) {
			cordinate.setStartToEndTransversalLinePositions(viewModel)
   	 }
	}
	if (isCardClicked[2] && isCardClicked[4] && isCardClicked[6]){
    	if ((cards[2] && cards[4] && cards[6]) || (!cards[2] && !cards[4] && !cards[6])) {
			cordinate.setEndToStartTransversalLinePositions(viewModel)
        }
    } 
}