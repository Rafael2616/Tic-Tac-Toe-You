package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinatesTest
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun WinnerLineLogic(
    viewModel: GameViewModel,
    cordinate: WinnerLineCoordinatesTest
) {  
    val card = viewModel.card.value
    val isCardClicked = viewModel.isCardClicked.value
    
	if (isCardClicked[0][0] && isCardClicked[0][1] && isCardClicked[0][2]){
		if ((card[0][0] && card[0][1] && card[0][2]) || (!card[0][0] && !card[0][1] && !card[0][2])) {
			cordinate.setFirstHorizontalLinePositions(viewModel)
		} 
	}
	if (isCardClicked[1][0] && isCardClicked[1][1] && isCardClicked[1][2]){
   	 if ((card[1][0] && card[1][1] && card[1][2]) || (!card[1][0] && !card[1][1] && !card[1][2])) {
     	   cordinate.setSecondHorizontalLinePositions(viewModel)
		} 
	}
	if (isCardClicked[2][0] && isCardClicked[2][1] && isCardClicked[2][2]){  
 	   if ((card[2][0] && card[2][1] && card[2][2]) || (!card[2][0] && !card[2][1] && !card[2][2])) {
			cordinate.setTertiaryHorizontalLinePositions(viewModel)
  	  }
	}
	if (isCardClicked[0][0] && isCardClicked[1][0] && isCardClicked[2][0]){
   	 if ((card[0][0] && card[1][0] && card[2][0]) || (!card[0][0] && !card[1][0] && !card[2][0])) {
			cordinate.setFirstVerticalLinePositions(viewModel)
    	}
	}
	if (isCardClicked[0][1] && isCardClicked[1][1] && isCardClicked[2][1]){ 
    	if ((card[0][1] && card[1][1] && card[2][1]) || (!card[0][1] && !card[1][1] && !card[2][1])) {
			cordinate.setSecondVerticalLinePositions(viewModel)
  	  }
	}
	if (isCardClicked[0][2] && isCardClicked[1][2] && isCardClicked[2][2]){
   	 if ((card[0][2] && card[1][2] && card[2][2]) || (!card[0][2] && !card[1][2] && !card[2][2])) {
			cordinate.setTertiaryVerticalLinePositions(viewModel)
    	}
	}
	if (isCardClicked[0][0] && isCardClicked[1][1] && isCardClicked[2][2]){
    	if ((card[0][0] && card[1][1] && card[2][2]) || (!card[0][0] && !card[1][1] && !card[2][2])) {
			cordinate.setStartToEndTransversalLinePositions(viewModel)
   	 }
	}
	if (isCardClicked[0][2] && isCardClicked[1][1] && isCardClicked[2][0]){
    	if ((card[0][2] && card[1][1] && card[2][0]) || (!card[0][2] && !card[1][1] && !card[2][0])) {
			cordinate.setEndToStartTransversalLinePositions(viewModel)
        }
    }
}