package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.activity.compose.*
//import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.*
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.util.HandleCardClick
import com.rafael.tictactoe.game.domain.util.WinnerLineLogic
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import kotlinx.coroutines.launch

@Composable
fun CardGrid(cards: List<Boolean>, isCardClicked: List<Boolean>, repository: GameRepository, coordinates: WinnerLineCoordinates, vm: GameViewModel) {
     val scope = rememberCoroutineScope()

     ConstraintLayout {
  	   val cardNumber = List(cards.size) { createRef() }
     
 	    val top1 = createGuidelineFromTop(0.55f)
  	   val top2 = createGuidelineFromTop(0.68f)
 	    val top3 = createGuidelineFromTop(0.81f)
 	    val bottom1 = createGuidelineFromBottom(0.44f)
 	    val bottom2 = createGuidelineFromBottom(0.23f)
 	    val bottom3 = createGuidelineFromBottom(0.02f)
  	   val start1 = createGuidelineFromStart(0.05f)
   	  val start2 = createGuidelineFromStart(0.4f)
  	   val start3 = createGuidelineFromStart(0.7f)
	     val end1 = createGuidelineFromEnd(0.7f)
 	    val end2 = createGuidelineFromEnd(0.4f)
  	   val end3 = createGuidelineFromEnd(0.05f)

			cards.forEachIndexed { index, _ ->
				val cardRef = cardNumber[index]

			Card(     
				shape = MaterialTheme.shapes.medium,
				modifier = Modifier
 	 		  .width(110.dp)
 		 	  .height(110.dp)
  		 	 .clickable(onClick = { scope.launch {
					HandleCardClick(repository, index); WinnerLineLogic(repository, coordinates); vm.updateExpandButton(false)}})
				.constrainAs(cardRef) {
					when (index) {
						0 -> {
							top.linkTo(top1)
							bottom.linkTo(bottom1); (cardNumber[3].top)
 					       start.linkTo(start1)
   					     end.linkTo(end1); (cardNumber[1].start)
						}
 			           1 -> {
							top.linkTo(top1)
    					    bottom.linkTo(bottom1); (cardNumber[4].top)
     					   start.linkTo(start2); (cardNumber[0].end)
     					   end.linkTo(end2); (cardNumber[2].start)
 					   }
  					  2 -> {
   					     top.linkTo(top1)
   					     bottom.linkTo(bottom1); (cardNumber[5].top)
    					    start.linkTo(start3); (cardNumber[1].end)
    					    end.linkTo(end3)
    				    }
						3 -> {
       					 top.linkTo(top2); (cardNumber[0].bottom)
     					   bottom.linkTo(bottom2); (cardNumber[6].top)
   					     start.linkTo(start1)
    					    end.linkTo(end1); (cardNumber[4].start)
      				  }
						4 -> {
    					    top.linkTo(top2); (cardNumber[1].bottom)
    					    bottom.linkTo(bottom2); (cardNumber[7].top)
   					     start.linkTo(start2); (cardNumber[3].end)
    					    end.linkTo(end2); (cardNumber[5].start)
      				  }
						5 -> {
							top.linkTo(top2); (cardNumber[2].bottom)
    					    bottom.linkTo(bottom2); (cardNumber[8].top)
   					     start.linkTo(start3); (cardNumber[4].end)
  					      end.linkTo(end3)
     				   }
						6 -> {
     					   top.linkTo(top3); (cardNumber[3].bottom)
    					    bottom.linkTo(bottom3)
     					   start.linkTo(start1)
    					    end.linkTo(end1); (cardNumber[7].start)
     				   }
						7 -> {
    					    top.linkTo(top3); (cardNumber[4].bottom)
    					    bottom.linkTo(bottom3) 
    					    start.linkTo(start2); (cardNumber[6].end)
     					   end.linkTo(end2); (cardNumber[8].start)
    				    }
						8 -> {
   					     top.linkTo(top3); (cardNumber[5].bottom)
    					    bottom.linkTo(bottom3)
    					    start.linkTo(start3); (cardNumber[7].end)
    					    end.linkTo(end3)
    				    }
 					else ->  error ("Inválid Index")
     				   }
    				}
    	  	  ) {
					Image(
						contentDescription = null,
 					   modifier = Modifier.fillMaxSize(),
 					   painter = painterResource(
						if (isCardClicked[index]) {
         				   if (cards[index]) R.drawable.x_image else R.drawable.circle_image
    	 		    } else {
       			     R.drawable.transparent_image
    				    }
					)
				)
			}       
		}
	}
}