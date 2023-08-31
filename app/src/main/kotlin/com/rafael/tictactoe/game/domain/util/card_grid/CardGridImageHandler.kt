package com.rafael.tictactoe.game.domain.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.painter.Painter
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

@Composable
fun CardGridImageHandler(index: Int): Painter {
    val viewModel = hiltViewModel<GameViewModel>()
	val xImage = painterResource(R.drawable.x_image)
	val circleImage = painterResource(R.drawable.circle_image)
	val transparentImage = painterResource(R.drawable.transparent_image)
    val cards by viewModel.cards.collectAsState()
	val isCardClicked by viewModel.isCardClicked.collectAsState()

   // if (winner) {
       // return transparentImage
   // }
	val cardGridImage = if (isCardClicked[index]) {
    	if (cards[index]) xImage else circleImage
	} else {
		transparentImage
	}
	return cardGridImage
}