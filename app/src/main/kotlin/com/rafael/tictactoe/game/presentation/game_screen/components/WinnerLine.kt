package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.activity.compose.*
//import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.WinnerLineLogic
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

@Composable
fun WinnerLine(
viewModel: GameViewModel,
coordinate: WinnerLineCoordinates,
cards: List<Boolean>,
isCardClicked: List<Boolean>,
startX: Float,
startY: Float,
endX: Float,
endY: Float
) {

	WinnerLineLogic(viewModel, coordinate, cards, isCardClicked)

	Column(
		modifier = Modifier
			.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Canvas(
			modifier = Modifier
            	.fillMaxSize()
           	 .weight(1f)
		) {
			drawLine(
				color = Color.Yellow,
                strokeWidth = 20.dp.toPx(),
                start = Offset(x = startX, y = startY),
				end = Offset(x = endX, y = endY)
			)
        }
	}
}