package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.util.CardGridClickHandler
import com.rafael.tictactoe.game.domain.util.CardGridImageHandler
import com.rafael.tictactoe.game.domain.util.RandomCardSorter
import com.rafael.tictactoe.game.domain.util.WinnerLineLogic
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinatesTest
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import kotlinx.coroutines.launch

@Composable
fun CardGrid(coordinates: WinnerLineCoordinatesTest) {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val context = LocalContext.current
    val cards by viewModel.cards.collectAsState()
    val scope = rememberCoroutineScope()

    ConstraintLayout {
        val cardNumber = List(viewModel.cards.value.size) { createRef() }    
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
  		 	 .clickable(onClick = {
                    scope.launch {
		    			CardGridClickHandler(context, viewModel, settingsVM, index)
                    };
					WinnerLineLogic(viewModel, coordinates);	
					viewModel.updateIsExpandButtonShown(false)})
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
 				   painter = CardGridImageHandler(index)
				)
			}
		}
	}
}