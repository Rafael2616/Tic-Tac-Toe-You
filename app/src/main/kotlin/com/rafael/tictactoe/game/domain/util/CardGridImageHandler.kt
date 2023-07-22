package com.rafael.tictactoe.game.domain.util

import androidx.compose.runtime.*
import androidx.compose.ui.res.*
import androidx.compose.ui.graphics.painter.*
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

@Composable
fun CardGridImageHandler(
viewModel: GameViewModel,
index: Int
): Painter {

	val xImage = painterResource(R.drawable.x_image)
	val circleImage = painterResource(R.drawable.circle_image)
	val transparentImage = painterResource(R.drawable.transparent_image)
    val cards by viewModel.cards.collectAsState()
	val isCardClicked by viewModel.isCardClicked.collectAsState()

	val cardGridImage = if (isCardClicked[index]) {
    	if (cards[index]) xImage else circleImage
	} else {
		transparentImage
	}
	return cardGridImage
}